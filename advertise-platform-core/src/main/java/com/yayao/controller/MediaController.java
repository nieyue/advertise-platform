package com.yayao.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.Account;
import com.yayao.bean.Media;
import com.yayao.bean.Role;
import com.yayao.service.MediaService;
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
 * 媒体控制类
 * @author yy
 *
 */
@Api(tags={"media"},value="媒体",description="媒体管理")
@RestController
@RequestMapping("/media")
public class MediaController extends BaseController<Media,Long> {
	@Resource
	private MediaService mediaService;
	
	/**
	 * 媒体分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "媒体列表", notes = "媒体分页浏览")
	@ApiImplicitParams({
		@ApiImplicitParam(name="appId",value="appId",dataType="string", paramType = "query"),
		@ApiImplicitParam(name="readingNumber",value="阅读量",dataType="long", paramType = "query"),
		@ApiImplicitParam(name="sexManScale",value="性别男比例,单位%",dataType="double", paramType = "query"),
		@ApiImplicitParam(name="sexWomanScale",value="性别女比例,单位%",dataType="double", paramType = "query"),
		@ApiImplicitParam(name="funsTop1Address",value="粉丝top1地址",dataType="string", paramType = "query"),
		@ApiImplicitParam(name="funsTop2Address",value="粉丝top2地址",dataType="string", paramType = "query"),
		@ApiImplicitParam(name="funsTop3Address",value="粉丝top3地址",dataType="string", paramType = "query"),
		@ApiImplicitParam(name="auth",value="授权,1未授权，2已授权",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="takeOrder",value="是否接单,1是，2否",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="status",value="状态，1审核中、2正常、3审核未通过、4异常",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="mediaCateId",value="媒体类型id",dataType="long", paramType = "query"),
		@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
	    @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	    @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	    @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="mediaId"),
	    @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Media>> browsePagingMedia(
			@RequestParam(value="appId",required=false)String appId,
			@RequestParam(value="readingNumber",required=false)Long readingNumber,
			@RequestParam(value="sexManScale",required=false)Double sexManScale,
			@RequestParam(value="sexWomanScale",required=false)Double sexWomanScale,
			@RequestParam(value="funsTop1Address",required=false)String funsTop1Address,
			@RequestParam(value="funsTop2Address",required=false)String funsTop2Address,
			@RequestParam(value="funsTop3Address",required=false)String funsTop3Address,
			@RequestParam(value="auth",required=false)Integer auth,
			@RequestParam(value="takeOrder",required=false)Integer takeOrder,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="mediaCateId",required=false)Long mediaCateId,
			@RequestParam(value="accountId",required=false)Long accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="mediaId") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			Wrapper<Media> wrapper=new EntityWrapper<>();
		 	Map<String,Object> map=new HashMap<>();
		 	map.put("app_id", appId);
		 	map.put("reading_number", readingNumber);
		 	map.put("sex_man_scale", sexManScale);
		 	map.put("sex_woman_scale", sexWomanScale);
		 	map.put("funs_top1_address", funsTop1Address);
		 	map.put("funs_top2_address", funsTop2Address);
		 	map.put("funs_top3_address", funsTop3Address);
		 	map.put("auth", auth);
		 	map.put("take_order", takeOrder);
		 	map.put("status", status);
		 	map.put("media_cate_id", mediaCateId);
		 	map.put("account_id", accountId);
			wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
			StateResultList<List<Media>> rl = super.list(pageNum, pageSize, orderName, orderWay,wrapper);
			return rl;
	}
	/**
	 * 媒体修改
	 * @return
	 */
	@ApiOperation(value = "媒体修改", notes = "媒体修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Media>> updateMedia(
			@ModelAttribute Media media,HttpSession session)  {
		media.setUpdateDate(new Date());
		if(session.getAttribute("role")!=null&&((Role)session.getAttribute("role")).getName().equals("媒体主")){
			media.setStatus(1);//审核中
		}
		StateResultList<List<Media>> u = super.update(media);
		return u;
	}
	/**
	 * 媒体增加
	 * @return 
	 */
	@ApiOperation(value = "媒体增加", notes = "媒体增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Media>> addMedia(
			@ModelAttribute Media media, HttpSession session) {
		media.setCreateDate(new Date());
		media.setUpdateDate(new Date());
		media.setStatus(1);//审核中
		StateResultList<List<Media>> a = super.add(media);
		return a;
	}
	/**
	 * 媒体删除
	 * @return
	 */
	@ApiOperation(value = "媒体删除", notes = "媒体删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="mediaId",value="媒体ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Media>> delMedia(
			@RequestParam("mediaId") Long mediaId,HttpSession session)  {
		StateResultList<List<Media>> d = super.delete(mediaId);
		return d;
	}
	/**
	 * 媒体浏览数量
	 * @return
	 */
	@ApiOperation(value = "媒体数量", notes = "媒体数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="appId",value="appId",dataType="string", paramType = "query"),
			@ApiImplicitParam(name="readingNumber",value="阅读量",dataType="long", paramType = "query"),
			@ApiImplicitParam(name="sexManScale",value="性别男比例,单位%",dataType="double", paramType = "query"),
			@ApiImplicitParam(name="sexWomanScale",value="性别女比例,单位%",dataType="double", paramType = "query"),
			@ApiImplicitParam(name="funsTop1Address",value="粉丝top1地址",dataType="string", paramType = "query"),
			@ApiImplicitParam(name="funsTop2Address",value="粉丝top2地址",dataType="string", paramType = "query"),
			@ApiImplicitParam(name="funsTop3Address",value="粉丝top3地址",dataType="string", paramType = "query"),
			@ApiImplicitParam(name="auth",value="授权,1未授权，2已授权",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="takeOrder",value="是否接单,1是，2否",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="status",value="状态，1审核中、2正常、3审核未通过、4异常",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="mediaCateId",value="媒体类型id",dataType="long", paramType = "query"),
			@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="appId",required=false)String appId,
			@RequestParam(value="readingNumber",required=false)Long readingNumber,
			@RequestParam(value="sexManScale",required=false)Double sexManScale,
			@RequestParam(value="sexWomanScale",required=false)Double sexWomanScale,
			@RequestParam(value="funsTop1Address",required=false)String funsTop1Address,
			@RequestParam(value="funsTop2Address",required=false)String funsTop2Address,
			@RequestParam(value="funsTop3Address",required=false)String funsTop3Address,
			@RequestParam(value="auth",required=false)Integer auth,
			@RequestParam(value="takeOrder",required=false)Integer takeOrder,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="mediaCateId",required=false)Long mediaCateId,
			@RequestParam(value="accountId",required=false)Long accountId,
			HttpSession session)  {
		Wrapper<Media> wrapper=new EntityWrapper<>();
	 	Map<String,Object> map=new HashMap<>();
		map.put("app_id", appId);
		map.put("reading_number", readingNumber);
		map.put("sex_man_scale", sexManScale);
		map.put("sex_woman_scale", sexWomanScale);
		map.put("funs_top1_address", funsTop1Address);
		map.put("funs_top2_address", funsTop2Address);
		map.put("funs_top3_address", funsTop3Address);
		map.put("auth", auth);
		map.put("take_order", takeOrder);
		map.put("status", status);
		map.put("media_cate_id", mediaCateId);
		map.put("account_id", accountId);
		wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
		StateResultList<List<Integer>> c = super.count(wrapper);
		return c;
	}
	/**
	 * 媒体单个加载
	 * @return
	 */
	@ApiOperation(value = "媒体单个加载", notes = "媒体单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="mediaId",value="媒体ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Media>> loadMedia(
			@RequestParam("mediaId") Long mediaId,HttpSession session)  {
		 StateResultList<List<Media>> l = super.load(mediaId);
		 return l;
	}
	
}
