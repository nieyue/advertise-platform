package com.yayao.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yayao.bean.*;
import com.yayao.exception.CommonRollbackException;
import com.yayao.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.util.MyDESutil;
import com.yayao.util.MyDom4jUtil;
@Service
public class AccountServiceImpl extends BaseServiceImpl<Account,Long> implements AccountService{
	@Autowired
	RoleService roleService;
	@Autowired
	ThirdInfoService thirdInfoService;
	@Autowired
	FinanceService financeService;
	@Autowired
	CompanyService companyService;
	@Autowired
	CollectService collectService;
	/**
	 * 登录
	 */
	@Override
	public Account loginAccount(String adminName, String password, Long accountId) {
		Account account = null;
		Wrapper<Account> wrapper=new EntityWrapper<>();
	 	Map<String,Object> map=new HashMap<String,Object>();
	 	map.put("phone", adminName);
	 	map.put("password", password==null?null:MyDESutil.getMD5(password));
	 	map.put("account_id", accountId);
	 	wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
	 	Map<String,Object> map2=new HashMap<String,Object>();
	 	map2.put("email", adminName);
	 	map2.put("password", password==null?null:MyDESutil.getMD5(password));
	 	map2.put("account_id", accountId);
	 	wrapper.orNew().allEq(MyDom4jUtil.getNoNullMap(map2));
	 	//wrapper.or().allEq(MyDom4jUtil.getNoNullMap(map2));
	 	List<Account> al = super.list(1,1,null,null,wrapper);
	 	if(al.size()>0){
	 		account=al.get(0);
 			Role role = roleService.load(account.getRoleId());
 			account.setRole(role);
 			account.setLoginDate(new Date());
 			super.update(account);
	 	}

	 	return account;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean add(Account t) {
		boolean b = super.add(t);
		//财务
		Finance finance =new Finance();
		finance.setAccountId(t.getAccountId());
		finance.setUpdateDate(new Date());
		finance.setCreateDate(new Date());
		finance.setBaseProfit(0.0);
		finance.setConsume(0.0);
		finance.setFrozen(0.0);
		finance.setIncome(0.0);
		finance.setMoney(0.0);
		finance.setRecharge(0.0);
		finance.setPassword(MyDESutil.getMD5("888888"));
		finance.setRefund(0.0);
		finance.setWithdrawals(0.0);
		b=financeService.add(finance);
		//公司信息
		Company company=new Company();
		company.setAccountId(t.getAccountId());
		company.setCreateDate(new Date());
		company.setUpdateDate(new Date());
		b=companyService.add(company);
		//第三方信息
		ThirdInfo ti=new ThirdInfo();
		ti.setAccountId(t.getAccountId());
		ti.setCreateDate(new Date());
		ti.setUpdateDate(new Date());
		b=thirdInfoService.add(ti);
		//汇总
		Collect collect=new Collect();
		collect.setWaitRelease(0);
		collect.setReleased(0);
		collect.setCompleted(0);
		collect.setCancelled(0);
		collect.setSettled(0);
		collect.setFuns(0l);
		collect.setReadingNumber(0l);
		collect.setConsume(0.0);
		collect.setIncome(0.0);
		collect.setCreateDate(new Date());
		collect.setUpdateDate(new Date());
		collect.setAccountId(t.getAccountId());
		b=collectService.add(collect);
		return b;
	}
	@Override
	public List<Account> list(int pageNum, int pageSize, String orderName, String orderWay, Wrapper<Account> wrapper) {
				List<Account> rl = super.list(pageNum, pageSize, orderName, orderWay, wrapper);
				if(rl!=null&&rl.size()>0){
			 		rl.forEach((a)->{
			 			Role role = roleService.load(a.getRoleId());
			 			a.setRole(role);
			 		});
				}
				return rl;
	}
	@Override
	public Account load(Long accountId) {
		Account a =super.load(accountId);
		if(!StringUtils.isEmpty(a)){			
		Role role =roleService.load(a.getRoleId());
		a.setRole(role);
		}
	 	return a;
	}
	
}
