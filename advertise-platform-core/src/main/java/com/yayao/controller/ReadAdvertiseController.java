package com.yayao.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.ReadAdvertise;
import com.yayao.service.ReadAdvertiseService;
import com.yayao.util.MyDom4jUtil;
import com.yayao.util.StateResultList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 阅读广告控制类
 * @author yy
 *
 */
@Api(tags={"readAdvertise"},value="阅读广告",description="阅读广告管理")
@RestController
@RequestMapping("/readAdvertise")
public class ReadAdvertiseController extends BaseController<ReadAdvertise,Long> {
	@Resource
	private ReadAdvertiseService readAdvertiseService;
	
	/**
	 * 阅读广告分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "阅读广告列表", notes = "阅读广告分页浏览")
	@ApiImplicitParams({
		@ApiImplicitParam(name="type",value="广告形式,1图文、2贴片",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="status",value="状态，1待支付、2待审核、3投放中、4暂停中、5已完成",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
	    @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	    @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	    @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="readAdvertiseId"),
	    @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<ReadAdvertise>> browsePagingReadAdvertise(
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="accountId",required=false)Long accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="readAdvertiseId") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			Wrapper<ReadAdvertise> wrapper=new EntityWrapper<>();
		 	Map<String,Object> map=new HashMap<>();
		 	map.put("type", type);
		 	map.put("status", status);
		 	map.put("account_id", accountId);
			wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
			StateResultList<List<ReadAdvertise>> rl = super.list(pageNum, pageSize, orderName, orderWay,wrapper);
			return rl;
	}
	/**
	 * 阅读广告修改
	 * @return
	 */
	@ApiOperation(value = "阅读广告修改", notes = "阅读广告修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<ReadAdvertise>> updateReadAdvertise(
			@ModelAttribute ReadAdvertise readAdvertise,HttpSession session)  {
		readAdvertise.setUpdateDate(new Date());
		StateResultList<List<ReadAdvertise>> u = super.update(readAdvertise);
		return u;
	}
	/**
	 * 阅读广告增加
	 * @return 
	 */
	@ApiOperation(value = "阅读广告增加", notes = "阅读广告增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<ReadAdvertise>> addReadAdvertise(
			@ModelAttribute ReadAdvertise readAdvertise, HttpSession session) {
		readAdvertise.setCreateDate(new Date());
		readAdvertise.setUpdateDate(new Date());
		StateResultList<List<ReadAdvertise>> a = super.add(readAdvertise);
		return a;
	}
	/**
	 * 阅读广告删除
	 * @return
	 */
	@ApiOperation(value = "阅读广告删除", notes = "阅读广告删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="readAdvertiseId",value="阅读广告ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<ReadAdvertise>> delReadAdvertise(
			@RequestParam("readAdvertiseId") Long readAdvertiseId,HttpSession session)  {
		StateResultList<List<ReadAdvertise>> d = super.delete(readAdvertiseId);
		return d;
	}
	/**
	 * 阅读广告浏览数量
	 * @return
	 */
	@ApiOperation(value = "阅读广告数量", notes = "阅读广告数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="type",value="广告形式,1图文、2贴片",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="status",value="状态，1待支付、2待审核、3投放中、4暂停中、5已完成",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="accountId",required=false)Long accountId,
			HttpSession session)  {
		Wrapper<ReadAdvertise> wrapper=new EntityWrapper<>();
	 	Map<String,Object> map=new HashMap<>();
	 	map.put("type", type);
	 	map.put("status", status);
	 	map.put("account_id", accountId);
		wrapper.allEq(MyDom4jUtil.getNoNullMap(map));

		StateResultList<List<Integer>> c = super.count(wrapper);
		return c;
	}
	/**
	 * 阅读广告单个加载
	 * @return
	 */
	@ApiOperation(value = "阅读广告单个加载", notes = "阅读广告单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="readAdvertiseId",value="阅读广告ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<ReadAdvertise>> loadReadAdvertise(
			@RequestParam("readAdvertiseId") Long readAdvertiseId,HttpSession session)  {
		 StateResultList<List<ReadAdvertise>> l = super.load(readAdvertiseId);
		 return l;
	}
	
}
