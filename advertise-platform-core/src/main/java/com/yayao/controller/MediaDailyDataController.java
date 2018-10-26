package com.yayao.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.MediaDailyData;
import com.yayao.service.MediaDailyDataService;
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
 * 媒体日数据控制类
 * @author yy
 *
 */
@Api(tags={"mediaDailyData"},value="媒体日数据",description="媒体日数据管理")
@RestController
@RequestMapping("/mediaDailyData")
public class MediaDailyDataController extends BaseController<MediaDailyData,Long> {
	@Resource
	private MediaDailyDataService mediaDailyDataService;
	
	/**
	 * 媒体日数据分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "媒体日数据列表", notes = "媒体日数据分页浏览")
	@ApiImplicitParams({
		@ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		@ApiImplicitParam(name="mediaId",value="媒体id",dataType="long", paramType = "query"),
		@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
	    @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	    @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	    @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="mediaDailyDataId"),
	    @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MediaDailyData>> browsePagingMediaDailyData(
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="mediaId",required=false)Long mediaId,
			@RequestParam(value="accountId",required=false)Long accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="mediaDailyDataId") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			Wrapper<MediaDailyData> wrapper=new EntityWrapper<>();
		 	Map<String,Object> map=new HashMap<>();
		 	map.put("create_date", createDate);
		 	map.put("media_id", mediaId);
		 	map.put("account_id", accountId);
			wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
			StateResultList<List<MediaDailyData>> rl = super.list(pageNum, pageSize, orderName, orderWay,wrapper);
			return rl;
	}
	/**
	 * 媒体日数据修改
	 * @return
	 */
	@ApiOperation(value = "媒体日数据修改", notes = "媒体日数据修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MediaDailyData>> updateMediaDailyData(
			@ModelAttribute MediaDailyData mediaDailyData,HttpSession session)  {
		mediaDailyData.setUpdateDate(new Date());
		StateResultList<List<MediaDailyData>> u = super.update(mediaDailyData);
		return u;
	}
	/**
	 * 媒体日数据增加
	 * @return 
	 */
	@ApiOperation(value = "媒体日数据增加", notes = "媒体日数据增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MediaDailyData>> addMediaDailyData(
			@ModelAttribute MediaDailyData mediaDailyData, HttpSession session) {
		mediaDailyData.setCreateDate(new Date());
		mediaDailyData.setUpdateDate(new Date());
		StateResultList<List<MediaDailyData>> a = super.add(mediaDailyData);
		return a;
	}
	/**
	 * 媒体日数据删除
	 * @return
	 */
	@ApiOperation(value = "媒体日数据删除", notes = "媒体日数据删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="mediaDailyDataId",value="媒体日数据ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MediaDailyData>> delMediaDailyData(
			@RequestParam("mediaDailyDataId") Long mediaDailyDataId,HttpSession session)  {
		StateResultList<List<MediaDailyData>> d = super.delete(mediaDailyDataId);
		return d;
	}
	/**
	 * 媒体日数据浏览数量
	 * @return
	 */
	@ApiOperation(value = "媒体日数据数量", notes = "媒体日数据数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
			@ApiImplicitParam(name="mediaId",value="媒体id",dataType="long", paramType = "query"),
			@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="mediaId",required=false)Long mediaId,
			@RequestParam(value="accountId",required=false)Long accountId,
			HttpSession session)  {
		Wrapper<MediaDailyData> wrapper=new EntityWrapper<>();
	 	Map<String,Object> map=new HashMap<>();
		map.put("create_date", createDate);
		map.put("media_id", mediaId);
		map.put("account_id", accountId);
		wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
		StateResultList<List<Integer>> c = super.count(wrapper);
		return c;
	}
	/**
	 * 媒体日数据单个加载
	 * @return
	 */
	@ApiOperation(value = "媒体日数据单个加载", notes = "媒体日数据单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="mediaDailyDataId",value="媒体日数据ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<MediaDailyData>> loadMediaDailyData(
			@RequestParam("mediaDailyDataId") Long mediaDailyDataId,HttpSession session)  {
		 StateResultList<List<MediaDailyData>> l = super.load(mediaDailyDataId);
		 return l;
	}
	
}
