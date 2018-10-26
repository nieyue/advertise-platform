package com.yayao.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.*;
import com.yayao.exception.AccountIsNotExistException;
import com.yayao.exception.CommonRollbackException;
import com.yayao.exception.PayException;
import com.yayao.service.*;
import com.yayao.util.Arith;
import com.yayao.util.MyDom4jUtil;
import com.yayao.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FinanceServiceImpl extends BaseServiceImpl<Finance,Long> implements FinanceService {
    @Autowired
    FinanceRecordService financeRecordService;
    @Autowired
    NoticeService noticeService;
    @Autowired
    AccountService accountService;
    @Autowired
    ReadAdvertiseService readAdvertiseService;
    @Autowired
    ConfigService configService;
    @Autowired
    CollectService collectService;

    @Value("${myPugin.projectDomainUrl}")
    String projectDomainUrl;


    //管理员充值
    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public Finance rechargeFinance(Long accountId, Integer method, Double money) {
        if(money<=0){
            throw new CommonRollbackException("金额必须大于0");	//账户不存在
        }
        Account a = accountService.load(accountId);
        if(a==null){
            throw new AccountIsNotExistException();	//账户不存在
        }
		/*if(a.getAuth()==null||a.getAuth()==0){//没认证
			throw new AccountNotAuthException();//账户未认证
		}*/
		/*if(a.getAuth()==1){//审核中
			throw new AccountAuthAuditException();//账户审核中
		}*/
        //获取配置信息
        List<Config> cl = configService.list(1, 1, null, null, null);
        if(cl.size()<=0){
            throw new PayException();
        }
        Config config = cl.get(0);
        //税金
        Double tax= Arith.mul(money,config.getTaxRate());
        //平台服务费
        Double brokerage=Arith.mul(money,config.getServiceProportion());
        //实际金额
        Double realMoney=Arith.sub(Arith.sub(money,tax),brokerage);
        //财务增加
        Wrapper<Finance> wrapper=new EntityWrapper<>();
        Map<String,Object> map=new HashMap<>();
        map.put("account_id", accountId);
        wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
        List<Finance> fl = super.list(1, 1, null, null, wrapper);
        if(fl.size()<=0){
            throw new PayException();
        }
        Finance finance = fl.get(0);
        finance.setUpdateDate(new Date());
        finance.setMoney(Arith.add(finance.getMoney(),realMoney));
        finance.setRecharge(Arith.add(finance.getRecharge(),realMoney));
        boolean b = super.update(finance);
        if(!b){
            throw new PayException();
        }
        //财务记录增加
        FinanceRecord fr=new FinanceRecord();
        fr.setAccountId(accountId);
        fr.setMethod(method);//方式，1支付宝，2微信,3百度钱包,4Paypal,5网银,6ios内购,7余额
        fr.setType(1);//类型，1充值、2消费，3退款，4冻结，5解冻，6收入，7提现
        fr.setTransactionNumber(SnowflakeIdWorker.getId().toString());//交易单号与支付单号相同
        fr.setBrokerage(brokerage);//平台服务费
        fr.setTax(tax);//税金
        fr.setMoney(money);//原金额
        fr.setRealMoney(realMoney);//实际金额
        fr.setStatus(2);//状态，默认1待处理，2成功，3已拒绝
        fr.setCreateDate(new Date());
        fr.setUpdateDate(new Date());
        b=financeRecordService.add(fr);//新增财务记录
        if(!b){
            throw new PayException();
        }
        //通知
        Notice notice=new Notice();
        notice.setIsRead(1);//是否阅读，默认1未读，2已读
        notice.setRegion(2);//范围，1全局，2个人
        notice.setType(2);//类型，默认1系统消息，2充值，3退款，4提现
        notice.setTitle("充值");
        notice.setImgAddress(projectDomainUrl+"/resources/img/daozhang.png");
        String name="";
        if(!StringUtils.isEmpty(a.getPhone())){
            name=a.getPhone();
        }else if(!StringUtils.isEmpty(a.getEmail())){
            name=a.getEmail();
        }
        notice.setContent("账户"+name+"充值成功");
        notice.setStatus(2);//状态，默认为0正常，1审核中，2申请成功，3申请失败
        notice.setCreateDate(new Date());
        notice.setUpdateDate(new Date());
        notice.setAccountId(accountId);
        b=noticeService.add(notice);
        if(!b){
            throw new PayException();
        }
        return finance;
    }
    //阅读广告支付
    @Transactional(propagation=Propagation.REQUIRED)
    @Override
    public Finance readAdvertisePayment(Long accountId, Long readAdvertiseId, Integer method) {
        Account a = accountService.load(accountId);

        if(a==null){
            throw new AccountIsNotExistException();	//账户不存在
        }
        //获取阅读广告
        ReadAdvertise readAdvertise = readAdvertiseService.load(readAdvertiseId);
        Double minTotalPrice=500.0;
        if(readAdvertise.getTotalPrice()<minTotalPrice){
            throw new CommonRollbackException("总预算不得低于"+minTotalPrice);
        }
        Double minUnitPrice=0.1;
        if(readAdvertise.getUnitPrice()<minUnitPrice){
            throw new CommonRollbackException("广告单价不得低于"+minUnitPrice);
        }
        if(readAdvertise.getStartDate().after(readAdvertise.getEndDate())){
            throw new CommonRollbackException("开始时间必须在结束时间之前");
        }
        readAdvertise.setStatus(3);//发布中
        boolean b = readAdvertiseService.update(readAdvertise);
        if(!b){
            throw new PayException();
        }
        //获取配置信息
        List<Config> cl = configService.list(1, 1, null, null, null);
        if(cl.size()<=0){
            throw new PayException();
        }
        Config config = cl.get(0);
        //税金
        //Double tax=Arith.mul(readAdvertise.getTotalPrice(),config.getTaxRate());
        //平台服务费
        //Double brokerage=Arith.mul(readAdvertise.getTotalPrice(),config.getServiceProportion());
        //实际金额
        //Double realMoney=Arith.sub(Arith.sub(readAdvertise.getTotalPrice(),tax),brokerage);
        //财务减少
        Wrapper<Finance> wrapper=new EntityWrapper<>();
        Map<String,Object> map=new HashMap<>();
        map.put("account_id", accountId);
        wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
        List<Finance> fl = super.list(1, 1, null, null, wrapper);
        if(fl.size()<=0){
            throw new PayException();
        }
        Finance finance = fl.get(0);
        finance.setUpdateDate(new Date());
        //余额减少
        finance.setMoney(Arith.sub(finance.getMoney(),readAdvertise.getTotalPrice()));
        //冻结金额增加
        finance.setFrozen(Arith.add(finance.getFrozen(),readAdvertise.getTotalPrice()));
        b = super.update(finance);
        if(!b){
            throw new PayException();
        }
        //财务记录增加
        FinanceRecord fr=new FinanceRecord();
        fr.setAdvertiseId(readAdvertise.getReadAdvertiseId());//广告Id
        fr.setAdvertiseName(readAdvertise.getName());//广告名称
        fr.setAccountId(accountId);
        fr.setMethod(method);//方式，1支付宝，2微信,3百度钱包,4Paypal,5网银,6ios内购,7余额
        fr.setType(4);//类型，1充值、2消费，3退款，4冻结，5解冻，6收入，7提现
        fr.setTransactionNumber(SnowflakeIdWorker.getId().toString());//交易单号与支付单号相同
        fr.setBrokerage(0.0);//平台服务费
        fr.setTax(0.0);//税金
        fr.setMoney(readAdvertise.getTotalPrice());//原金额
        fr.setRealMoney(readAdvertise.getTotalPrice());//实际金额
        fr.setStatus(2);//状态，默认1待处理，2成功，3已拒绝
        fr.setCreateDate(new Date());
        fr.setUpdateDate(new Date());
        b=financeRecordService.add(fr);//新增财务记录
        if(!b){
            throw new PayException();
        }
        //汇总
        Wrapper<Collect> wrapper2=new EntityWrapper<>();
        Map<String,Object> map2=new HashMap<>();
        map2.put("account_id", accountId);
        wrapper2.allEq(MyDom4jUtil.getNoNullMap(map2));
        List<Collect> collectlist = collectService.list(1, 1, null, null, wrapper2);
        if(collectlist.size()<=0){
            throw new PayException();
        }
        Collect collect = collectlist.get(0);
        collect.setUpdateDate(new Date());
        collect.setAccountId(accountId);
        collect.setWaitRelease(collect.getWaitRelease()-1);//待发布
        collect.setReleased(collect.getReleased()+1);//发布中
        b=collectService.update(collect);
        if(!b){
            throw new PayException();
        }
        return finance;
    }
}
