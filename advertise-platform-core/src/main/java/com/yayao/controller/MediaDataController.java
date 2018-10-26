package com.yayao.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.MediaData;
import com.yayao.service.MediaDataService;
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
 * 媒体详细数据控制类
 * @author yy
 *
 */
@Api(tags={"mediaData"},value="媒体详细数据",description="媒体详细数据管理")
@RestController
@RequestMapping("/mediaData")
public class MediaDataController extends BaseController<MediaData,Long> {
	@Resource
	private MediaDataService mediaDataService;
	
	/**
	 * 媒体详细数据分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "媒体详细数据列表", notes = "媒体详细数据分页浏览")
	@ApiImplicitParams({
		@ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		@ApiImplicitParam(name="mediaId",value="媒体id",dataType="long", paramType = "query"),
		@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
	    @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	    @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	    @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="mediaDataId"),
	    @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MediaData>> browsePagingMediaData(
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="mediaId",required=false)Long mediaId,
			@RequestParam(value="accountId",required=false)Long accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="mediaDataId") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			Wrapper<MediaData> wrapper=new EntityWrapper<>();
		 	Map<String,Object> map=new HashMap<>();
		 	map.put("create_date", createDate);
		 	map.put("media_id", mediaId);
		 	map.put("account_id", accountId);
			wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
			StateResultList<List<MediaData>> rl = super.list(pageNum, pageSize, orderName, orderWay,wrapper);
			return rl;
	}
	/**
	 * 媒体详细数据修改
	 * @return
	 */
	@ApiOperation(value = "媒体详细数据修改", notes = "媒体详细数据修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MediaData>> updateMediaData(
			@ModelAttribute MediaData mediaData,HttpSession session)  {
		mediaData.setUpdateDate(new Date());
		StateResultList<List<MediaData>> u = super.update(mediaData);
		return u;
	}
	/**
	 * 媒体详细数据增加
	 * @return 
	 */
	@ApiOperation(value = "媒体详细数据增加", notes = "媒体详细数据增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MediaData>> addMediaData(
			@ModelAttribute MediaData mediaData, HttpSession session) {
		mediaData.setCreateDate(new Date());
		mediaData.setUpdateDate(new Date());
		StateResultList<List<MediaData>> a = super.add(mediaData);
		return a;
	}
	/**
	 * 媒体详细数据删除
	 * @return
	 */
	@ApiOperation(value = "媒体详细数据删除", notes = "媒体详细数据删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="mediaDataId",value="媒体详细数据ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MediaData>> delMediaData(
			@RequestParam("mediaDataId") Long mediaDataId,HttpSession session)  {
		StateResultList<List<MediaData>> d = super.delete(mediaDataId);
		return d;
	}
	/**
	 * 媒体详细数据浏览数量
	 * @return
	 */
	@ApiOperation(value = "媒体详细数据数量", notes = "媒体详细数据数量查询")
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
		Wrapper<MediaData> wrapper=new EntityWrapper<>();
	 	Map<String,Object> map=new HashMap<>();
		map.put("create_date", createDate);
		map.put("media_id", mediaId);
		map.put("account_id", accountId);
		wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
		StateResultList<List<Integer>> c = super.count(wrapper);
		return c;
	}
	/**
	 * 媒体详细数据单个加载
	 * @return
	 */
	@ApiOperation(value = "媒体详细数据单个加载", notes = "媒体详细数据单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="mediaDataId",value="媒体详细数据ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<MediaData>> loadMediaData(
			@RequestParam("mediaDataId") Long mediaDataId,HttpSession session)  {
		 StateResultList<List<MediaData>> l = super.load(mediaDataId);
		 return l;
	}
	
}
