package com.yayao.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.RolePermission;
import com.yayao.service.RolePermissionService;
import com.yayao.util.MyDom4jUtil;
import com.yayao.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 角色权限控制类
 * @author yy
 *
 */
@Api(tags={"rolePermission"},value="角色权限",description="角色权限管理")
@RestController
@RequestMapping("/rolePermission")
public class RolePermissionController extends BaseController<RolePermission,Long> {
	@Resource
	private RolePermissionService rolePermissionService;
	
	/**
	 * 角色权限分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "角色权限列表", notes = "角色权限分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="region",value="范围，1公共，2自身",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="roleId",value="角色id",dataType="long", paramType = "query"),
	  @ApiImplicitParam(name="permissionId",value="权限id",dataType="long", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="rolePermissionId"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<RolePermission>> browsePagingRolePermission(
			@RequestParam(value="region",required=false)Integer region,
			@RequestParam(value="roleId",required=false)Long roleId,
			@RequestParam(value="permissionId",required=false)Long permissionId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="rolePermissionId") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			Wrapper<RolePermission> wrapper=new EntityWrapper<>();
		 	Map<String,Object> map=new HashMap<>();
		 	map.put("region", region);
		 	map.put("role_id", roleId);
		 	map.put("permission_id", permissionId);
		 	wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
			StateResultList<List<RolePermission>> rl = super.list(pageNum, pageSize, orderName, orderWay,wrapper);
			return rl;
	}
	/**
	 * 角色权限修改
	 * @return
	 */
	@ApiOperation(value = "角色权限修改", notes = "角色权限修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<RolePermission>> updateRolePermission(@ModelAttribute RolePermission rolePermission,HttpSession session)  {
		rolePermission.setUpdateDate(new Date());
		StateResultList<List<RolePermission>> u = super.update(rolePermission);
		return u;
	}
	/**
	 * 角色权限增加
	 * @return 
	 */
	@ApiOperation(value = "角色权限增加", notes = "角色权限增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<RolePermission>> addRolePermission(@ModelAttribute RolePermission rolePermission, HttpSession session) {
		rolePermission.setUpdateDate(new Date());
		StateResultList<List<RolePermission>> a = super.add(rolePermission);
		return a;
	}
	/**
	 * 角色权限删除
	 * @return
	 */
	@ApiOperation(value = "角色权限删除", notes = "角色权限删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="rolePermissionId",value="角色权限ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<RolePermission>> delRolePermission(@RequestParam("rolePermissionId") Long rolePermissionId,HttpSession session)  {
		StateResultList<List<RolePermission>> d = super.delete(rolePermissionId);
		return d;
	}
	/**
	 * 角色权限浏览数量
	 * @return
	 */
	@ApiOperation(value = "角色权限数量", notes = "角色权限数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="region",value="范围，1公共，2自身",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="roleId",value="角色id",dataType="long", paramType = "query"),
			@ApiImplicitParam(name="permissionId",value="权限id",dataType="long", paramType = "query")
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody  StateResultList<List<Integer>> countAll(
			@RequestParam(value="region",required=false)Integer region,
			@RequestParam(value="roleId",required=false)Long roleId,
			@RequestParam(value="permissionId",required=false)Long permissionId,
			HttpSession session)  {
		Wrapper<RolePermission> wrapper=new EntityWrapper<>();
	 	Map<String,Object> map=new HashMap<>();
	 	map.put("region", region);
	 	map.put("role_id", roleId);
	 	map.put("permission_id", permissionId);
	 	wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
		StateResultList<List<Integer>> c = super.count(wrapper);
		return c;
	}
	/**
	 * 角色权限单个加载
	 * @return
	 */
	@ApiOperation(value = "角色权限单个加载", notes = "角色权限单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="rolePermissionId",value="角色权限ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<RolePermission>> loadRolePermission(@RequestParam("rolePermissionId") Long rolePermissionId,HttpSession session)  {
		StateResultList<List<RolePermission>> l = super.load(rolePermissionId);
		 return l;
	}
	
}
