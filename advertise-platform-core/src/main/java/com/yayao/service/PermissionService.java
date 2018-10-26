package com.yayao.service;

import com.yayao.bean.Permission;

/**
 * 权限逻辑层接口
 * @author yy
 *
 */
public interface PermissionService extends BaseService<Permission, Long>{
	/** 初始化权限 */	
	public boolean initPermission();	
}
