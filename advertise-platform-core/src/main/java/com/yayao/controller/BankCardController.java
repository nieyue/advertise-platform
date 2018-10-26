package com.yayao.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.BankCard;
import com.yayao.service.BankCardService;
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
 * 银行卡控制类
 * @author yy
 *
 */
@Api(tags={"bankCard"},value="银行卡",description="银行卡管理")
@RestController
@RequestMapping("/bankCard")
public class BankCardController extends BaseController<BankCard,Long> {
	@Resource
	private BankCardService bankCardService;
	
	/**
	 * 银行卡分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "银行卡列表", notes = "银行卡分页浏览")
	@ApiImplicitParams({
		@ApiImplicitParam(name="accountId",value="账户Id",dataType="long", paramType = "query"),
	  	@ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  	@ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
		@ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="bankCardId"),
	    @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<BankCard>> browsePagingBankCard(
			@RequestParam(value="accountId",required=false)Long accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="bankCardId") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			Wrapper<BankCard> wrapper=new EntityWrapper<>();
		 	Map<String,Object> map=new HashMap<>();
		 	map.put("account_id", accountId);
		 	wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
			StateResultList<List<BankCard>> rl = super.list(pageNum, pageSize, orderName, orderWay,wrapper);
			return rl;
	}
	/**
	 * 银行卡修改
	 * @return
	 */
	@ApiOperation(value = "银行卡修改", notes = "银行卡修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<BankCard>> updateBankCard(
			@ModelAttribute BankCard bankCard,HttpSession session)  {
		bankCard.setUpdateDate(new Date());
		StateResultList<List<BankCard>> u = super.update(bankCard);
		return u;
	}
	/**
	 * 银行卡增加
	 * @return 
	 */
	@ApiOperation(value = "银行卡增加", notes = "银行卡增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<BankCard>> addBankCard(
			@ModelAttribute BankCard bankCard,
			HttpSession session) {
		bankCard.setCreateDate(new Date());
		bankCard.setUpdateDate(new Date());
		StateResultList<List<BankCard>> a = super.add(bankCard);
		return a;
	}
	/**
	 * 银行卡删除
	 * @return
	 */
	@ApiOperation(value = "银行卡删除", notes = "银行卡删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="bankCardId",value="银行卡ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<BankCard>> delBankCard(
			@RequestParam("bankCardId") Long bankCardId,HttpSession session)  {
		StateResultList<List<BankCard>> d = super.delete(bankCardId);
		return d;
	}
	/**
	 * 银行卡浏览数量
	 * @return
	 */
	@ApiOperation(value = "银行卡数量", notes = "银行卡数量查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="accountId",value="账户Id",dataType="long", paramType = "query"),
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="accountId",required=false)Long accountId,
			HttpSession session)  {
		Wrapper<BankCard> wrapper=new EntityWrapper<>();
	 	Map<String,Object> map=new HashMap<>();
		map.put("account_id", accountId);
	 	wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
		StateResultList<List<Integer>> c = super.count(wrapper);
		return c;
	}
	/**
	 * 银行卡单个加载
	 * @return
	 */
	@ApiOperation(value = "银行卡单个加载", notes = "银行卡单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="bankCardId",value="银行卡ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<BankCard>> loadBankCard(@RequestParam("bankCardId") Long bankCardId,HttpSession session)  {
		 StateResultList<List<BankCard>> l = super.load(bankCardId);
		 return l;
	}
	
}
