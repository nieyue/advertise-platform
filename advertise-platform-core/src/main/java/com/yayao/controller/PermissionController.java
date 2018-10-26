package com.yayao.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.yayao.bean.Permission;
import com.yayao.service.PermissionService;
import com.yayao.util.MyDom4jUtil;
import com.yayao.util.ResultUtil;
import com.yayao.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 权限控制类
 * @author yy
 *
 */
@Api(tags={"permission"},value="权限",description="权限管理")
@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseController<Permission,Long> {
	@Resource
	private PermissionService permissionService;
	
	/**
	 * 权限分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "权限列表", notes = "权限分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="type",value="权限类型，默认0开放，1鉴权",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="managerName",value="权限管理名，模糊查询",dataType="string", paramType = "query"),
	  @ApiImplicitParam(name="name",value="权限名，模糊查询",dataType="string", paramType = "query"),
	  @ApiImplicitParam(name="route",value="权限路由，模糊查询",dataType="string", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="permissionId"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Permission>> browsePagingPermission(
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="managerName",required=false)String managerName,
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="route",required=false)String route,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="permissionId") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			Wrapper<Permission> wrapper=new EntityWrapper<>();
		 	Map<String,Object> map=new HashMap<>();
		 	map.put("type", type);
		 	wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
		 	Map<String,Object> map2=new HashMap<>();
		 	map2.put("manager_name", managerName);
		 	map2.put("name", name);
		 	map2.put("route", route);
		 	Map<String, Object> nmap2 = MyDom4jUtil.getNoNullMap(map2);
		 	Iterator<Entry<String, Object>> iterator = nmap2.entrySet().iterator();
		 	while ( iterator.hasNext()) {
		 		Entry<String, Object> entry = iterator.next();
		 		wrapper.andNew().like(entry.getKey(), (String) entry.getValue());
			}
			StateResultList<List<Permission>> rl = super.list(pageNum, pageSize, orderName, orderWay,wrapper);
			return rl;
	}
	/**
	 * 初始化权限
	 * @return
	 */
	@ApiOperation(value = "初始化权限", notes = "初始化权限")
	@RequestMapping(value = "/init", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Permission>> initPermission()  {
		boolean um = permissionService.initPermission();
		if(um){
			return ResultUtil.getSlefSRSuccessList(null);
		}
		return ResultUtil.getSlefSRList(40000, "已经是最新，不需更新", null);
	}
	/**
	 * 权限修改
	 * @return
	 */
	@ApiOperation(value = "权限修改", notes = "权限修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Permission>> updatePermission(@ModelAttribute Permission permission,HttpSession session)  {
		permission.setUpdateDate(new Date());
		StateResultList<List<Permission>> u = super.update(permission);
		return u;
	}
	/**
	 * 权限增加
	 * @return 
	 */
	@ApiOperation(value = "权限增加", notes = "权限增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Permission>> addPermission(@ModelAttribute Permission permission, HttpSession session) {
		permission.setUpdateDate(new Date());
		StateResultList<List<Permission>> a = super.add(permission);
		return a;
	}
	/**
	 * 权限删除
	 * @return
	 */
	@ApiOperation(value = "权限删除", notes = "权限删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="permissionId",value="权限ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Permission>> delPermission(@RequestParam("permissionId") Long permissionId,HttpSession session)  {
		StateResultList<List<Permission>> d = super.delete(permissionId);
		return d;
	}
	/**
	 * 权限浏览数量
	 * @return
	 */
	@ApiOperation(value = "权限数量", notes = "权限数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="type",value="权限类型，默认0开放，1鉴权",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="managerName",value="权限管理名，模糊查询",dataType="string", paramType = "query"),
			@ApiImplicitParam(name="name",value="权限名，模糊查询",dataType="string", paramType = "query"),
			@ApiImplicitParam(name="route",value="权限路由，模糊查询",dataType="string", paramType = "query")
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="managerName",required=false)String managerName,
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="route",required=false)String route,
			HttpSession session)  {
		Wrapper<Permission> wrapper=new EntityWrapper<>();
	 	Map<String,Object> map=new HashMap<String,Object>();
	 	map.put("type", type);
	 	wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
	 	Map<String,Object> map2=new HashMap<>();
	 	map2.put("manager_name", managerName);
	 	map2.put("name", name);
	 	map2.put("route", route);
	 	Map<String, Object> nmap2 = MyDom4jUtil.getNoNullMap(map2);
	 	Iterator<Entry<String, Object>> iterator = nmap2.entrySet().iterator();
	 	while ( iterator.hasNext()) {
	 		Entry<String, Object> entry = iterator.next();
	 		wrapper.andNew().like(entry.getKey(), (String) entry.getValue());
		}
		StateResultList<List<Integer>> c = super.count(wrapper);
		return c;
	}
	/**
	 * 权限单个加载
	 * @return
	 */
	@ApiOperation(value = "权限单个加载", notes = "权限单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="permissionId",value="权限ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Permission>> loadPermission(@RequestParam("permissionId") Long permissionId,HttpSession session)  {
		 StateResultList<List<Permission>> l = super.load(permissionId);
		 return l;
	}
	
}
