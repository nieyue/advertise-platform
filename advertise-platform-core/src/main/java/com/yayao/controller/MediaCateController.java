package com.yayao.controller;

import com.yayao.bean.MediaCate;
import com.yayao.service.MediaCateService;
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
 * 媒体类型控制类
 * @author yy
 *
 */
@Api(tags={"mediaCate"},value="媒体类型",description="媒体类型管理")
@RestController
@RequestMapping("/mediaCate")
public class MediaCateController extends BaseController<MediaCate,Long> {
	@Resource
	private MediaCateService MediaCateService;
	
	/**
	 * 媒体类型分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "媒体类型列表", notes = "媒体类型分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="updateDate"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MediaCate>> list(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="updateDate") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			StateResultList<List<MediaCate>> rl = super.list(pageNum, pageSize, orderName, orderWay,null);
			return rl;
	}
	/**
	 * 媒体类型修改
	 * @return
	 */
	@ApiOperation(value = "媒体类型修改", notes = "媒体类型修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MediaCate>> update(@ModelAttribute MediaCate mediaCate,HttpSession session)  {
		mediaCate.setUpdateDate(new Date());
		StateResultList<List<MediaCate>> u = super.update(mediaCate);
		return u;
	}
	/**
	 * 媒体类型增加
	 * @return 
	 */
	@ApiOperation(value = "媒体类型增加", notes = "媒体类型增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MediaCate>> add(@ModelAttribute MediaCate mediaCate, HttpSession session) {
		mediaCate.setUpdateDate(new Date());
		StateResultList<List<MediaCate>> a = super.add(mediaCate);
		return a;
	}
	/**
	 * 媒体类型删除
	 * @return
	 */
	@ApiOperation(value = "媒体类型删除", notes = "媒体类型删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="mediaCateId",value="媒体类型ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<MediaCate>> delete(@RequestParam("mediaCateId") Long mediaCateId,HttpSession session)  {
		StateResultList<List<MediaCate>> d = super.delete(mediaCateId);
		return d;
	}
	/**
	 * 媒体类型浏览数量
	 * @return
	 */
	@ApiOperation(value = "媒体类型数量", notes = "媒体类型数量查询")
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(HttpSession session)  {
		StateResultList<List<Integer>> c = super.count(null);
		return c;
	}
	/**
	 * 媒体类型单个加载
	 * @return
	 */
	@ApiOperation(value = "媒体类型单个加载", notes = "媒体类型单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="mediaCateId",value="媒体类型ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<MediaCate>> loadMediaCate(@RequestParam("mediaCateId") Long mediaCateId,HttpSession session)  {
		 StateResultList<List<MediaCate>> l = super.load(mediaCateId);
		 return l;
	}
	
}
