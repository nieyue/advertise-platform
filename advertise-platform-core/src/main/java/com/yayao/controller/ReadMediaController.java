package com.yayao.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.ReadMedia;
import com.yayao.service.ReadMediaService;
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
 * 阅读媒体控制类
 * @author yy
 *
 */
@Api(tags={"readMedia"},value="阅读媒体",description="阅读媒体管理")
@RestController
@RequestMapping("/readMedia")
public class ReadMediaController extends BaseController<ReadMedia,Long> {
	@Resource
	private ReadMediaService readMediaService;
	
	/**
	 * 阅读媒体分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "阅读媒体列表", notes = "阅读媒体分页浏览")
	@ApiImplicitParams({
		@ApiImplicitParam(name="position",value="广告位置,1头条，2二条，3三条，4四条，5五条，6六条",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="status",value="状态，1待审核、2待发布、3发布中、4待结算、5已完成、6审核未通过、7已取消",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="mediaId",value="媒体id",dataType="long", paramType = "query"),
		@ApiImplicitParam(name="readMediaId",value="阅读媒体id",dataType="long", paramType = "query"),
		@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
	    @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	    @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	    @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="readMediaId"),
	    @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<ReadMedia>> browsePagingReadMedia(
			@RequestParam(value="position",required=false)Integer position,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="mediaId",required=false)Long mediaId,
			@RequestParam(value="readMediaId",required=false)Long readMediaId,
			@RequestParam(value="accountId",required=false)Long accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="readMediaId") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			Wrapper<ReadMedia> wrapper=new EntityWrapper<>();
		 	Map<String,Object> map=new HashMap<>();
		 	map.put("position", position);
		 	map.put("status", status);
		 	map.put("media_id", mediaId);
		 	map.put("read_media_id", readMediaId);
		 	map.put("account_id", accountId);
			wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
			StateResultList<List<ReadMedia>> rl = super.list(pageNum, pageSize, orderName, orderWay,wrapper);
			return rl;
	}
	/**
	 * 阅读媒体修改
	 * @return
	 */
	@ApiOperation(value = "阅读媒体修改", notes = "阅读媒体修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<ReadMedia>> updateReadMedia(
			@ModelAttribute ReadMedia readMedia,HttpSession session)  {
		readMedia.setUpdateDate(new Date());
		StateResultList<List<ReadMedia>> u = super.update(readMedia);
		return u;
	}
	/**
	 * 阅读媒体增加
	 * @return 
	 */
	@ApiOperation(value = "阅读媒体增加", notes = "阅读媒体增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<ReadMedia>> addReadMedia(
			@ModelAttribute ReadMedia readMedia, HttpSession session) {
		readMedia.setCreateDate(new Date());
		readMedia.setUpdateDate(new Date());
		StateResultList<List<ReadMedia>> a = super.add(readMedia);
		return a;
	}
	/**
	 * 阅读媒体删除
	 * @return
	 */
	@ApiOperation(value = "阅读媒体删除", notes = "阅读媒体删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="readMediaId",value="阅读媒体ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<ReadMedia>> delReadMedia(
			@RequestParam("readMediaId") Long readMediaId,HttpSession session)  {
		StateResultList<List<ReadMedia>> d = super.delete(readMediaId);
		return d;
	}
	/**
	 * 阅读媒体浏览数量
	 * @return
	 */
	@ApiOperation(value = "阅读媒体数量", notes = "阅读媒体数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="position",value="广告位置,1头条，2二条，3三条，4四条，5五条，6六条",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="status",value="状态，1待审核、2待发布、3发布中、4待结算、5已完成、6审核未通过、7已取消",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="mediaId",value="媒体id",dataType="long", paramType = "query"),
			@ApiImplicitParam(name="readMediaId",value="阅读媒体id",dataType="long", paramType = "query"),
			@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="position",required=false)Integer position,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="mediaId",required=false)Long mediaId,
			@RequestParam(value="readMediaId",required=false)Long readMediaId,
			@RequestParam(value="accountId",required=false)Long accountId,
			HttpSession session)  {
		Wrapper<ReadMedia> wrapper=new EntityWrapper<>();
	 	Map<String,Object> map=new HashMap<>();
	 	map.put("position", position);
	 	map.put("status", status);
	 	map.put("media_id", mediaId);
	 	map.put("read_media_id", readMediaId);
	 	map.put("account_id", accountId);
		wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
		StateResultList<List<Integer>> c = super.count(wrapper);
		return c;
	}
	/**
	 * 阅读媒体单个加载
	 * @return
	 */
	@ApiOperation(value = "阅读媒体单个加载", notes = "阅读媒体单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="readMediaId",value="阅读媒体ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<ReadMedia>> loadReadMedia(
			@RequestParam("readMediaId") Long readMediaId,HttpSession session)  {
		 StateResultList<List<ReadMedia>> l = super.load(readMediaId);
		 return l;
	}
	
}
