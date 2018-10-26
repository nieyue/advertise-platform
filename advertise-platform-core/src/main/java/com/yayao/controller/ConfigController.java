package com.yayao.controller;

import com.yayao.bean.Config;
import com.yayao.service.ConfigService;
import com.yayao.util.StateResultList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


/**
 * 配置控制类
 * @author yy
 *
 */
@Api(tags={"config"},value="配置",description="配置管理")
@RestController
@RequestMapping("/config")
public class ConfigController extends BaseController<Config,Long> {
	@Resource
	private ConfigService configService;
	
	/**
	 * 配置分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "配置列表", notes = "配置分页浏览")
	@ApiImplicitParams({
	    @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	    @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	    @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="configId"),
	    @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Config>> browsePagingConfig(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="configId") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			StateResultList<List<Config>> rl = super.list(pageNum, pageSize, orderName, orderWay,null);
			return rl;
	}
	/**
	 * 配置修改
	 * @return
	 */
	@ApiOperation(value = "配置修改", notes = "配置修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Config>> updateConfig(
			@ModelAttribute Config config,HttpSession session)  {
		config.setUpdateDate(new Date());
		StateResultList<List<Config>> u = super.update(config);
		return u;
	}
	/**
	 * 配置增加
	 * @return 
	 */
	@ApiOperation(value = "配置增加", notes = "配置增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Config>> addConfig(
			@ModelAttribute Config config, HttpSession session) {
		config.setCreateDate(new Date());
		config.setUpdateDate(new Date());
		StateResultList<List<Config>> a = super.add(config);
		return a;
	}
	/**
	 * 配置删除
	 * @return
	 */
	@ApiOperation(value = "配置删除", notes = "配置删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="configId",value="配置ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Config>> delConfig(
			@RequestParam("configId") Long configId,HttpSession session)  {
		StateResultList<List<Config>> d = super.delete(configId);
		return d;
	}
	/**
	 * 配置浏览数量
	 * @return
	 */
	@ApiOperation(value = "配置数量", notes = "配置数量查询")
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			HttpSession session)  {
		StateResultList<List<Integer>> c = super.count(null);
		return c;
	}
	/**
	 * 配置单个加载
	 * @return
	 */
	@ApiOperation(value = "配置单个加载", notes = "配置单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="configId",value="配置ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Config>> loadConfig(
			@RequestParam("configId") Long configId,HttpSession session)  {
		 StateResultList<List<Config>> l = super.load(configId);
		 return l;
	}
	
}
