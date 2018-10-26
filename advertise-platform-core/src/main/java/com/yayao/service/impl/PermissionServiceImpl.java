package com.yayao.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.Permission;
import com.yayao.bean.RolePermission;
import com.yayao.dao.PermissionDao;
import com.yayao.service.PermissionService;
import com.yayao.service.RolePermissionService;
import com.yayao.shiro.ShiroService;
import com.yayao.shiro.ShiroUtil;
import com.yayao.util.MyDom4jUtil;
@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission,Long> implements PermissionService{
	@Autowired
	PermissionService permissionService;
	@Autowired
	RolePermissionService rolePermissionService;
	@Autowired
	ShiroService shiroService;
	@Autowired
	ShiroUtil shiroUtil;
	@Override
	public boolean initPermission() {
		boolean b = shiroUtil.initPermission();
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean add(Permission permission) {
		Lock lock=new ReentrantLock();
		lock.lock();
		boolean b=false;
		try {
			Wrapper<Permission> wrapper=new EntityWrapper<>();
		 	Map<String,Object> map=new HashMap<>();
		 	map.put("route", permission.getRoute());
		 	wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
		 	List<Permission> pl = super.list(1, Integer.MAX_VALUE, null, null, wrapper);
			if(pl.size()>0){
				return b;
			}
			permission.setUpdateDate(new Date());
			b = super.add(permission);
			if(b){
			 	List<Permission> pl2 = super.list(1, Integer.MAX_VALUE, null, null, null);
				//同步权限数据
				shiroService.updatePermission(pl2);
			}
		}finally {
			lock.unlock();
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delete(Long permissionId) {
		boolean b = super.delete(permissionId);
		if(b){
			Wrapper<RolePermission> wrapper=new EntityWrapper<>();
		 	Map<String,Object> map=new HashMap<>();
		 	map.put("permission_id", permissionId);
		 	wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
			List<RolePermission> rpl = rolePermissionService.list(1, Integer.MAX_VALUE, null, null, wrapper);
			for (RolePermission rolePermission : rpl) {
				b=rolePermissionService.delete(rolePermission.getRolePermissionId());
			}
			if(b){
				//同步权限数据
				shiroService.updatePermission(super.list(1, Integer.MAX_VALUE, null, null, null));
			}
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean update(Permission permission) {
		permission.setUpdateDate(new Date());
		boolean b = super.update(permission);
		if(b){
			//同步权限数据
			shiroService.updatePermission(super.list(1, Integer.MAX_VALUE, null, null, null));
		}
		return b;
	}
	
}
