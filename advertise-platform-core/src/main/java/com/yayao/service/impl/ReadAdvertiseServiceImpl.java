package com.yayao.service.impl;

import com.yayao.bean.Collect;
import com.yayao.bean.ReadAdvertise;
import com.yayao.exception.CommonRollbackException;
import com.yayao.service.CollectService;
import com.yayao.service.ReadAdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReadAdvertiseServiceImpl extends BaseServiceImpl<ReadAdvertise,Long> implements ReadAdvertiseService {
    @Autowired
    CollectService collectService;
    @Override
    public boolean add(ReadAdvertise readAdvertise) {
        //最低单价
        Double minUnitPrice=0.3;
        if(readAdvertise.getUnitPrice()<minUnitPrice){
            throw new CommonRollbackException("广告单价最低"+minUnitPrice);
        }
        //最低广告预算
        Double minTotalPrice=500.0;
        if(readAdvertise.getTotalPrice()<minTotalPrice){
            throw new CommonRollbackException("广告预算最低"+minTotalPrice);
        }
        if(readAdvertise.getAccountId()==null){
            throw new CommonRollbackException("账户id缺失");
        }
        readAdvertise.setQualityPrice(0.0);
        readAdvertise.setStatus(1);//状态，1待支付、2待审核、3投放中、4暂停中、5已完成
        boolean b = super.add(readAdvertise);
        //汇总
        Collect collect=new Collect();
        collect.setUpdateDate(new Date());
        collect.setAccountId(readAdvertise.getAccountId());
        collect.setWaitRelease(collect.getWaitRelease()+1);//待发布
        b = collectService.update(collect);
        return b;
    }
}
