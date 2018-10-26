package com.yayao.service;

import com.yayao.bean.Account;

/**
 * 账户逻辑层接口
 * @author yy
 *
 */
public interface AccountService extends BaseService<Account, Long>{
	/**
	 * 登录
	 */
	 Account loginAccount(String adminName, String password, Long accountId);
}
