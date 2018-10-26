package com.yayao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.Permission;
import com.yayao.bean.RolePermission;
import com.yayao.service.PermissionService;
import com.yayao.service.RolePermissionService;
@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission,Long> implements RolePermissionService{
	@Resource
	PermissionService permissionService;
	@Override
	public RolePermission load(Long rolePermissionId) {
		RolePermission r = super.load(rolePermissionId);
		Permission permission = permissionService.load(r.getPermissionId());
		r.setPermission(permission);
		return r;
	}
	@Override
	public List<RolePermission> list(int pageNum, int pageSize, String orderName, String orderWay,
			Wrapper<RolePermission> wrapper) {
		List<RolePermission> list=super.list(pageNum, pageSize, orderName, orderWay, wrapper);
		list.forEach((e)->{
			Permission permission = permissionService.load(e.getPermissionId());
			e.setPermission(permission);
		});
		return list;
	}
}
