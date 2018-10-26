package com.yayao.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.PositionMedia;
import com.yayao.service.PositionMediaService;
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
 * 位置媒体控制类
 * @author yy
 *
 */
@Api(tags={"positionMedia"},value="位置媒体",description="位置媒体管理")
@RestController
@RequestMapping("/positionMedia")
public class PositionMediaController extends BaseController<PositionMedia,Long> {
	@Resource
	private PositionMediaService positionMediaService;
	
	/**
	 * 位置媒体分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "位置媒体列表", notes = "位置媒体分页浏览")
	@ApiImplicitParams({
		@ApiImplicitParam(name="position",value="广告位置,1头条，2二条，3三条，4四条，5五条，6六条",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="status",value="状态，1待接单，2已接单，3已拒单，4执行中，5已完成，6已取消",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="payStatus",value="支付状态，1待支付，2已支付",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="mediaId",value="媒体id",dataType="long", paramType = "query"),
		@ApiImplicitParam(name="positionAdvertiseId",value="位置广告id",dataType="long", paramType = "query"),
		@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
	    @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	    @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	    @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="positionMediaId"),
	    @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<PositionMedia>> browsePagingPositionMedia(
			@RequestParam(value="position",required=false)Integer position,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="payStatus",required=false)Integer payStatus,
			@RequestParam(value="mediaId",required=false)Long mediaId,
			@RequestParam(value="positionAdvertiseId",required=false)Long positionAdvertiseId,
			@RequestParam(value="accountId",required=false)Long accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="positionMediaId") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			Wrapper<PositionMedia> wrapper=new EntityWrapper<>();
		 	Map<String,Object> map=new HashMap<>();
		 	map.put("position", position);
		 	map.put("status", status);
		 	map.put("pay_status", payStatus);
		 	map.put("media_id", mediaId);
		 	map.put("position_advertise_id", positionAdvertiseId);
		 	map.put("account_id", accountId);
			wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
			StateResultList<List<PositionMedia>> rl = super.list(pageNum, pageSize, orderName, orderWay,wrapper);
			return rl;
	}
	/**
	 * 位置媒体修改
	 * @return
	 */
	@ApiOperation(value = "位置媒体修改", notes = "位置媒体修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<PositionMedia>> updatePositionMedia(
			@ModelAttribute PositionMedia positionMedia,HttpSession session)  {
		positionMedia.setUpdateDate(new Date());
		StateResultList<List<PositionMedia>> u = super.update(positionMedia);
		return u;
	}
	/**
	 * 位置媒体增加
	 * @return 
	 */
	@ApiOperation(value = "位置媒体增加", notes = "位置媒体增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<PositionMedia>> addPositionMedia(
			@ModelAttribute PositionMedia positionMedia, HttpSession session) {
		positionMedia.setCreateDate(new Date());
		positionMedia.setUpdateDate(new Date());
		StateResultList<List<PositionMedia>> a = super.add(positionMedia);
		return a;
	}
	/**
	 * 位置媒体删除
	 * @return
	 */
	@ApiOperation(value = "位置媒体删除", notes = "位置媒体删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="positionMediaId",value="位置媒体ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<PositionMedia>> delPositionMedia(
			@RequestParam("positionMediaId") Long positionMediaId,HttpSession session)  {
		StateResultList<List<PositionMedia>> d = super.delete(positionMediaId);
		return d;
	}
	/**
	 * 位置媒体浏览数量
	 * @return
	 */
	@ApiOperation(value = "位置媒体数量", notes = "位置媒体数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="position",value="广告位置,1头条，2二条，3三条，4四条，5五条，6六条",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="status",value="状态，1待接单，2已接单，3已拒单，4执行中，5已完成，6已取消",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="payStatus",value="支付状态，1待支付，2已支付",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="mediaId",value="媒体id",dataType="long", paramType = "query"),
			@ApiImplicitParam(name="positionAdvertiseId",value="位置广告id",dataType="long", paramType = "query"),
			@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="position",required=false)Integer position,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="payStatus",required=false)Integer payStatus,
			@RequestParam(value="mediaId",required=false)Long mediaId,
			@RequestParam(value="positionAdvertiseId",required=false)Long positionAdvertiseId,
			@RequestParam(value="accountId",required=false)Long accountId,
			HttpSession session)  {
		Wrapper<PositionMedia> wrapper=new EntityWrapper<>();
	 	Map<String,Object> map=new HashMap<>();
		map.put("position", position);
		map.put("status", status);
		map.put("pay_status", payStatus);
		map.put("media_id", mediaId);
		map.put("position_advertise_id", positionAdvertiseId);
		map.put("account_id", accountId);
		wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
		StateResultList<List<Integer>> c = super.count(wrapper);
		return c;
	}
	/**
	 * 位置媒体单个加载
	 * @return
	 */
	@ApiOperation(value = "位置媒体单个加载", notes = "位置媒体单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="positionMediaId",value="位置媒体ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<PositionMedia>> loadPositionMedia(
			@RequestParam("positionMediaId") Long positionMediaId,HttpSession session)  {
		 StateResultList<List<PositionMedia>> l = super.load(positionMediaId);
		 return l;
	}
	
}
