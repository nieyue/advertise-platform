package com.yayao.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.ThirdInfo;
import com.yayao.service.ThirdInfoService;
import com.yayao.util.MyDom4jUtil;
import com.yayao.util.StateResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 第三方信息控制类
 * @author yy
 *
 */
@Api(tags={"thirdInfo"},value="第三方信息",description="第三方信息管理")
@RestController
@RequestMapping("/thirdInfo")
public class ThirdInfoController extends BaseController<ThirdInfo,Long> {
	@Resource
	private ThirdInfoService thirdInfoService;
	
	/**
	 * 第三方信息分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "第三方信息列表", notes = "第三方信息分页浏览")
	@ApiImplicitParams({
		@ApiImplicitParam(name="wxOpenid",value="微信openid",dataType="string", paramType = "query"),
		@ApiImplicitParam(name="wxUuid",value="微信uuid",dataType="string", paramType = "query"),
		@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
		@ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
		@ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
		@ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="updateDate"),
		@ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<ThirdInfo>> list(
			@RequestParam(value="wxOpenid",required=false)String wxOpenid,
			@RequestParam(value="wxUuid",required=false)String wxUuid,
			@RequestParam(value="accountId",required=false)Long accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="updateDate") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
		 	Wrapper<ThirdInfo> wrapper=new EntityWrapper<ThirdInfo>();
		 	Map<String,Object> map=new HashMap<String,Object>();
		 	map.put("wx_uuid", wxUuid);
		 	map.put("account_id", accountId);
		 	wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
		 	//openid为数据集合，所以，模糊查询
		 	Map<String,Object> likemap=new HashMap<String,Object>();
		 	likemap.put("wx_openid", wxOpenid);
		 	Map<String, Object> nlikemap = MyDom4jUtil.getNoNullMap(likemap);
		 	Set<Entry<String, Object>> newmaplie = nlikemap.entrySet();
		 	for (Entry<String, Object> entry : newmaplie) {
		 		wrapper.like(entry.getKey(),(String)entry.getValue());			
		 	}
			StateResultList<List<ThirdInfo>> rl = super.list(pageNum, pageSize, orderName, orderWay,wrapper);
			return rl;
	}
	/**
	 * 第三方信息修改
	 * @return
	 */
	@ApiOperation(value = "第三方信息修改", notes = "第三方信息修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<ThirdInfo>> update(@ModelAttribute ThirdInfo thirdInfo,HttpSession session)  {
		thirdInfo.setUpdateDate(new Date());
		StateResultList<List<ThirdInfo>> u = super.update(thirdInfo);
		return u;
	}
	/**
	 * 第三方信息增加
	 * @return 
	 */
	@ApiOperation(value = "第三方信息增加", notes = "第三方信息增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<ThirdInfo>> add(@ModelAttribute ThirdInfo thirdInfo, HttpSession session) {
		thirdInfo.setCreateDate(new Date());
		thirdInfo.setUpdateDate(new Date());
		StateResultList<List<ThirdInfo>> a = super.add(thirdInfo);
		return a;
	}
	/**
	 * 第三方信息删除
	 * @return
	 */
	@ApiOperation(value = "第三方信息删除", notes = "第三方信息删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="thirdInfoId",value="第三方信息ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<ThirdInfo>> delete(@RequestParam("thirdInfoId") Long thirdInfoId,HttpSession session)  {
		StateResultList<List<ThirdInfo>> d = super.delete(thirdInfoId);
		return d;
	}
	/**
	 * 第三方信息浏览数量
	 * @return
	 */
	@ApiOperation(value = "第三方信息数量", notes = "第三方信息数量查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="wxOpenid",value="微信openid",dataType="string", paramType = "query"),
		@ApiImplicitParam(name="wxUuid",value="微信uuid",dataType="string", paramType = "query"),
		@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query"),
	  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="wxOpenid",required=false)String wxOpenid,
			@RequestParam(value="wxUuid",required=false)String wxUuid,
			@RequestParam(value="accountId",required=false)Long accountId,
			HttpSession session)  {
	 	Wrapper<ThirdInfo> wrapper=new EntityWrapper<ThirdInfo>();
	 	Map<String,Object> map=new HashMap<String,Object>();
	 	map.put("wx_uuid", wxUuid);
	 	map.put("account_id", accountId);
	 	wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
	 	//openid为数据集合，所以，模糊查询
	 	Map<String,Object> likemap=new HashMap<String,Object>();
	 	likemap.put("wx_openid", wxOpenid);
	 	Map<String, Object> nlikemap = MyDom4jUtil.getNoNullMap(likemap);
	 	Set<Entry<String, Object>> newmaplie = nlikemap.entrySet();
	 	for (Entry<String, Object> entry : newmaplie) {
	 		wrapper.like(entry.getKey(),(String)entry.getValue());			
	 	}
		StateResultList<List<Integer>> c = super.count(wrapper);
		return c;
	}
	/**
	 * 第三方信息单个加载
	 * @return
	 */
	@ApiOperation(value = "第三方信息单个加载", notes = "第三方信息单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="thirdInfoId",value="第三方信息ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<ThirdInfo>> loadThirdInfo(@RequestParam("thirdInfoId") Long thirdInfoId,HttpSession session)  {
		 StateResultList<List<ThirdInfo>> l = super.load(thirdInfoId);
		 return l;
	}
	
}
