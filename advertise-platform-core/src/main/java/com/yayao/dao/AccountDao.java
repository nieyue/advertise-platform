package com.yayao.dao;

import com.yayao.bean.Account;

/**
 * 账户数据库接口
 * @author yy
 *
 */
public interface AccountDao extends BaseDao<Account>  {
	/**
	 * 登录
	 */
	 Account loginAccount(String adminName, String password, Long accountId);
}
