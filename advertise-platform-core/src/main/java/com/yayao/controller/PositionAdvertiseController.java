package com.yayao.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.PositionAdvertise;
import com.yayao.service.PositionAdvertiseService;
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
 * 位置广告控制类
 * @author yy
 *
 */
@Api(tags={"positionAdvertise"},value="位置广告",description="位置广告管理")
@RestController
@RequestMapping("/positionAdvertise")
public class PositionAdvertiseController extends BaseController<PositionAdvertise,Long> {
	@Resource
	private PositionAdvertiseService positionAdvertiseService;
	
	/**
	 * 位置广告分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "位置广告列表", notes = "位置广告分页浏览")
	@ApiImplicitParams({
		@ApiImplicitParam(name="position",value="广告位置,1多图文头条，2多图文二条，3多图文3-N条，4单图文",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="status",value="状态，1待支付、2待审核、3投放中、4暂停中、5已完成",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
	    @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	    @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	    @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="positionAdvertiseId"),
	    @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<PositionAdvertise>> browsePagingPositionAdvertise(
			@RequestParam(value="position",required=false)Integer position,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="accountId",required=false)Long accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="positionAdvertiseId") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			Wrapper<PositionAdvertise> wrapper=new EntityWrapper<>();
		 	Map<String,Object> map=new HashMap<>();
		 	map.put("position", position);
		 	map.put("status", status);
		 	map.put("account_id", accountId);
			wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
			StateResultList<List<PositionAdvertise>> rl = super.list(pageNum, pageSize, orderName, orderWay,wrapper);
			return rl;
	}
	/**
	 * 位置广告修改
	 * @return
	 */
	@ApiOperation(value = "位置广告修改", notes = "位置广告修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<PositionAdvertise>> updatePositionAdvertise(
			@ModelAttribute PositionAdvertise positionAdvertise,HttpSession session)  {
		positionAdvertise.setUpdateDate(new Date());
		StateResultList<List<PositionAdvertise>> u = super.update(positionAdvertise);
		return u;
	}
	/**
	 * 位置广告增加
	 * @return 
	 */
	@ApiOperation(value = "位置广告增加", notes = "位置广告增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<PositionAdvertise>> addPositionAdvertise(
			@ModelAttribute PositionAdvertise positionAdvertise, HttpSession session) {
		positionAdvertise.setCreateDate(new Date());
		positionAdvertise.setUpdateDate(new Date());
		StateResultList<List<PositionAdvertise>> a = super.add(positionAdvertise);
		return a;
	}
	/**
	 * 位置广告删除
	 * @return
	 */
	@ApiOperation(value = "位置广告删除", notes = "位置广告删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="positionAdvertiseId",value="位置广告ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<PositionAdvertise>> delPositionAdvertise(
			@RequestParam("positionAdvertiseId") Long positionAdvertiseId,HttpSession session)  {
		StateResultList<List<PositionAdvertise>> d = super.delete(positionAdvertiseId);
		return d;
	}
	/**
	 * 位置广告浏览数量
	 * @return
	 */
	@ApiOperation(value = "位置广告数量", notes = "位置广告数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="position",value="广告位置,1多图文头条，2多图文二条，3多图文3-N条，4单图文",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="status",value="状态，1待支付、2待审核、3投放中、4暂停中、5已完成",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="position",required=false)Integer position,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="accountId",required=false)Long accountId,
			HttpSession session)  {
		Wrapper<PositionAdvertise> wrapper=new EntityWrapper<>();
	 	Map<String,Object> map=new HashMap<>();
	 	map.put("position", position);
	 	map.put("status", status);
	 	map.put("account_id", accountId);
		wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
		StateResultList<List<Integer>> c = super.count(wrapper);
		return c;
	}
	/**
	 * 位置广告单个加载
	 * @return
	 */
	@ApiOperation(value = "位置广告单个加载", notes = "位置广告单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="positionAdvertiseId",value="位置广告ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<PositionAdvertise>> loadPositionAdvertise(
			@RequestParam("positionAdvertiseId") Long positionAdvertiseId,HttpSession session)  {
		 StateResultList<List<PositionAdvertise>> l = super.load(positionAdvertiseId);
		 return l;
	}
	
}
