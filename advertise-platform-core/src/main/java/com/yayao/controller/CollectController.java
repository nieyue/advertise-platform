package com.yayao.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.Collect;
import com.yayao.service.CollectService;
import com.yayao.util.MyDom4jUtil;
import com.yayao.util.StateResultList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 汇总控制类
 * @author yy
 *
 */
@Api(tags={"collect"},value="汇总",description="汇总管理")
@RestController
@RequestMapping("/collect")
public class CollectController extends BaseController<Collect,Long> {
	@Resource
	private CollectService collectService;
	
	/**
	 * 汇总分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "汇总列表", notes = "汇总分页浏览")
	@ApiImplicitParams({
		@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
	    @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	    @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	    @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="collectId"),
	    @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Collect>> browsePagingCollect(
			@RequestParam(value="accountId",required=false)Long accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="collectId") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			Wrapper<Collect> wrapper=new EntityWrapper<>();
		 	Map<String,Object> map=new HashMap<>();
		 	map.put("account_id", accountId);
			wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
			StateResultList<List<Collect>> rl = super.list(pageNum, pageSize, orderName, orderWay,wrapper);
			return rl;
	}
	/**
	 * 汇总修改
	 * @return
	 */
	@ApiOperation(value = "汇总修改", notes = "汇总修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Collect>> updateCollect(
			@ModelAttribute Collect collect,HttpSession session)  {
		collect.setUpdateDate(new Date());
		StateResultList<List<Collect>> u = super.update(collect);
		return u;
	}
	/**
	 * 汇总增加
	 * @return 
	 */
	@ApiOperation(value = "汇总增加", notes = "汇总增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Collect>> addCollect(
			@ModelAttribute Collect collect, HttpSession session) {
		collect.setCreateDate(new Date());
		collect.setUpdateDate(new Date());
		StateResultList<List<Collect>> a = super.add(collect);
		return a;
	}
	/**
	 * 汇总删除
	 * @return
	 */
	@ApiOperation(value = "汇总删除", notes = "汇总删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="collectId",value="汇总ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Collect>> delCollect(
			@RequestParam("collectId") Long collectId,HttpSession session)  {
		StateResultList<List<Collect>> d = super.delete(collectId);
		return d;
	}
	/**
	 * 汇总浏览数量
	 * @return
	 */
	@ApiOperation(value = "汇总数量", notes = "汇总数量查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="accountId",required=false)Long accountId,
			HttpSession session)  {
		Wrapper<Collect> wrapper=new EntityWrapper<>();
	 	Map<String,Object> map=new HashMap<>();
	 	map.put("account_id", accountId);
		wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
		StateResultList<List<Integer>> c = super.count(wrapper);
		return c;
	}
	/**
	 * 汇总单个加载
	 * @return
	 */
	@ApiOperation(value = "汇总单个加载", notes = "汇总单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="collectId",value="汇总ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Collect>> loadCollect(
			@RequestParam("collectId") Long collectId,HttpSession session)  {
		 StateResultList<List<Collect>> l = super.load(collectId);
		 return l;
	}
	
}
