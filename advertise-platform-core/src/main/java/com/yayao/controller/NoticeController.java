package com.yayao.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.Notice;
import com.yayao.service.NoticeService;
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
 * 通知控制类
 * @author yy
 *
 */
@Api(tags={"notice"},value="通知",description="通知管理")
@RestController
@RequestMapping("/notice")
public class NoticeController extends BaseController<Notice,Long> {
	@Resource
	private NoticeService noticeService;
	
	/**
	 * 通知分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "通知列表", notes = "通知分页浏览")
	@ApiImplicitParams({
		@ApiImplicitParam(name="region",value="范围，1全局，2个人",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="type",value="类型，1系统消息，2充值，3退款，4提现",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="isRead",value="是否阅读，默认1未读，2已读",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="status",value="状态，0正常，1审核中，2申请成功，3申请失败",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
	    @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	    @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	    @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="noticeId"),
	    @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Notice>> browsePagingNotice(
			@RequestParam(value="region",required=false)Integer region,
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="isRead",required=false)Integer isRead,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="accountId",required=false)Long accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="noticeId") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			Wrapper<Notice> wrapper=new EntityWrapper<>();
		 	Map<String,Object> map=new HashMap<>();
		 	map.put("region", region);
		 	map.put("type", type);
		 	map.put("is_read", isRead);
		 	map.put("status", status);
		 	map.put("account_id", accountId);
			wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
			StateResultList<List<Notice>> rl = super.list(pageNum, pageSize, orderName, orderWay,wrapper);
			return rl;
	}
	/**
	 * 通知修改
	 * @return
	 */
	@ApiOperation(value = "通知修改", notes = "通知修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Notice>> updateNotice(
			@ModelAttribute Notice notice,HttpSession session)  {
		notice.setUpdateDate(new Date());
		StateResultList<List<Notice>> u = super.update(notice);
		return u;
	}
	/**
	 * 通知增加
	 * @return 
	 */
	@ApiOperation(value = "通知增加", notes = "通知增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Notice>> addNotice(
			@ModelAttribute Notice notice, HttpSession session) {
		notice.setCreateDate(new Date());
		notice.setUpdateDate(new Date());
		StateResultList<List<Notice>> a = super.add(notice);
		return a;
	}
	/**
	 * 通知删除
	 * @return
	 */
	@ApiOperation(value = "通知删除", notes = "通知删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="noticeId",value="通知ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Notice>> delNotice(
			@RequestParam("noticeId") Long noticeId,HttpSession session)  {
		StateResultList<List<Notice>> d = super.delete(noticeId);
		return d;
	}
	/**
	 * 通知浏览数量
	 * @return
	 */
	@ApiOperation(value = "通知数量", notes = "通知数量查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="region",value="范围，1全局，2个人",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="type",value="类型，1系统消息，2充值，3退款，4提现",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="isRead",value="是否阅读，默认1未读，2已读",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="status",value="状态，0正常，1审核中，2申请成功，3申请失败",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="region",required=false)Integer region,
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="isRead",required=false)Integer isRead,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="accountId",required=false)Long accountId,
			HttpSession session)  {
		Wrapper<Notice> wrapper=new EntityWrapper<>();
	 	Map<String,Object> map=new HashMap<>();
	 	map.put("region", region);
	 	map.put("type", type);
	 	map.put("is_read", isRead);
	 	map.put("status", status);
	 	map.put("account_id", accountId);
		wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
		StateResultList<List<Integer>> c = super.count(wrapper);
		return c;
	}
	/**
	 * 通知单个加载
	 * @return
	 */
	@ApiOperation(value = "通知单个加载", notes = "通知单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="noticeId",value="通知ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Notice>> loadNotice(
			@RequestParam("noticeId") Long noticeId,HttpSession session)  {
		 StateResultList<List<Notice>> l = super.load(noticeId);
		 return l;
	}
	
}
