package com.yayao.service;

import com.yayao.bean.Finance;

/**
 * 财务逻辑层接口
 * @author yy
 *
 */
public interface FinanceService extends BaseService<Finance, Long>{
    //管理员充值
    Finance rechargeFinance(Long accountId,Integer method,Double money);
    //阅读广告支付
    Finance readAdvertisePayment(Long accountId,Long readAdvertiseId,Integer method);
}
