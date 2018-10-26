package com.yayao.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.MediaCollection;
import com.yayao.service.MediaCollectionService;
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
 * 媒体收藏控制类
 * @author yy
 *
 */
@Api(tags={"mediaCollection"},value="媒体收藏",description="媒体收藏管理")
@RestController
@RequestMapping("/mediaCollection")
public class MediaCollectionController extends BaseController<MediaCollection,Long> {
	@Resource
	private MediaCollectionService mediaCollectionService;
	
	/**
	 * 媒体收藏分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "媒体收藏列表", notes = "媒体收藏分页浏览")
	@ApiImplicitParams({
		@ApiImplicitParam(name="mediaId",value="媒体id",dataType="long", paramType = "query"),
		@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
	    @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	    @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	    @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="mediaId"),
	    @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MediaCollection>> browsePagingMediaCollection(
			@RequestParam(value="mediaId",required=false)Long mediaId,
			@RequestParam(value="accountId",required=false)Long accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="mediaId") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			Wrapper<MediaCollection> wrapper=new EntityWrapper<>();
		 	Map<String,Object> map=new HashMap<>();
		 	map.put("media_id", mediaId);
		 	map.put("account_id", accountId);
			wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
			StateResultList<List<MediaCollection>> rl = super.list(pageNum, pageSize, orderName, orderWay,wrapper);
			return rl;
	}
	/**
	 * 媒体收藏修改
	 * @return
	 */
	@ApiOperation(value = "媒体收藏修改", notes = "媒体收藏修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MediaCollection>> updateMediaCollection(
			@ModelAttribute MediaCollection mediaCollection,HttpSession session)  {
		mediaCollection.setUpdateDate(new Date());
		StateResultList<List<MediaCollection>> u = super.update(mediaCollection);
		return u;
	}
	/**
	 * 媒体收藏增加
	 * @return 
	 */
	@ApiOperation(value = "媒体收藏增加", notes = "媒体收藏增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MediaCollection>> addMediaCollection(
			@ModelAttribute MediaCollection mediaCollection, HttpSession session) {
		mediaCollection.setUpdateDate(new Date());
		StateResultList<List<MediaCollection>> a = super.add(mediaCollection);
		return a;
	}
	/**
	 * 媒体收藏删除
	 * @return
	 */
	@ApiOperation(value = "媒体收藏删除", notes = "媒体收藏删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="mediaCollectionId",value="媒体收藏ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MediaCollection>> delMediaCollection(
			@RequestParam("mediaCollectionId") Long mediaCollectionId,HttpSession session)  {
		StateResultList<List<MediaCollection>> d = super.delete(mediaCollectionId);
		return d;
	}
	/**
	 * 媒体收藏浏览数量
	 * @return
	 */
	@ApiOperation(value = "媒体收藏数量", notes = "媒体收藏数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="mediaId",value="媒体id",dataType="long", paramType = "query"),
			@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="mediaId",required=false)Long mediaId,
			@RequestParam(value="accountId",required=false)Long accountId,
			HttpSession session)  {
		Wrapper<MediaCollection> wrapper=new EntityWrapper<>();
	 	Map<String,Object> map=new HashMap<>();
		map.put("media_id", mediaId);
		map.put("account_id", accountId);
		wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
		StateResultList<List<Integer>> c = super.count(wrapper);
		return c;
	}
	/**
	 * 媒体收藏单个加载
	 * @return
	 */
	@ApiOperation(value = "媒体收藏单个加载", notes = "媒体收藏单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="mediaCollectionId",value="媒体收藏ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<MediaCollection>> loadMediaCollection(
			@RequestParam("mediaCollectionId") Long mediaCollectionId,HttpSession session)  {
		 StateResultList<List<MediaCollection>> l = super.load(mediaCollectionId);
		 return l;
	}
	
}
