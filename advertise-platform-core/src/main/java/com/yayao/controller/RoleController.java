package com.yayao.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yayao.bean.Role;
import com.yayao.service.RoleService;
import com.yayao.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 角色控制类
 * @author yy
 *
 */
@Api(tags={"role"},value="角色",description="角色管理")
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController<Role,Long> {
	@Resource
	private RoleService roleService;
	
	/**
	 * 角色分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "角色列表", notes = "角色分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="updateDate"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Role>> list(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="updateDate") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			StateResultList<List<Role>> rl = super.list(pageNum, pageSize, orderName, orderWay,null);
			return rl;
	}
	/**
	 * 角色修改
	 * @return
	 */
	@ApiOperation(value = "角色修改", notes = "角色修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Role>> update(@ModelAttribute Role role,HttpSession session)  {
		role.setUpdateDate(new Date());
		StateResultList<List<Role>> u = super.update(role);
		return u;
	}
	/**
	 * 角色增加
	 * @return 
	 */
	@ApiOperation(value = "角色增加", notes = "角色增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Role>> add(@ModelAttribute Role role, HttpSession session) {
		role.setUpdateDate(new Date());
		StateResultList<List<Role>> a = super.add(role);
		return a;
	}
	/**
	 * 角色删除
	 * @return
	 */
	@ApiOperation(value = "角色删除", notes = "角色删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="roleId",value="角色ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Role>> delete(@RequestParam("roleId") Long roleId,HttpSession session)  {
		StateResultList<List<Role>> d = super.delete(roleId);
		return d;
	}
	/**
	 * 角色浏览数量
	 * @return
	 */
	@ApiOperation(value = "角色数量", notes = "角色数量查询")
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(HttpSession session)  {
		StateResultList<List<Integer>> c = super.count(null);
		return c;
	}
	/**
	 * 角色单个加载
	 * @return
	 */
	@ApiOperation(value = "角色单个加载", notes = "角色单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="roleId",value="角色ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Role>> loadRole(@RequestParam("roleId") Long roleId,HttpSession session)  {
		 StateResultList<List<Role>> l = super.load(roleId);
		 return l;
	}
	
}
