package com.yayao.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.Finance;
import com.yayao.service.*;
import com.yayao.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * 财务控制类
 * @author yy
 *
 */
@Api(tags={"finance"},value="财务",description="财务管理")
@RestController
@RequestMapping("/finance")
public class FinanceController extends BaseController<Finance,Long> {
	@Autowired
	 FinanceService financeService;
	@Autowired
	 FinanceRecordService financeRecordService;
	@Autowired
	 NoticeService noticeService;
	@Autowired
	 AccountService accountService;
	@Autowired
	 ReadAdvertiseService readAdvertiseService;
	@Autowired
	 ConfigService configService;
	@Autowired
	 CollectService collectService;

	/**
	 * 财务分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "财务列表", notes = "财务分页浏览")
	@ApiImplicitParams({
		@ApiImplicitParam(name="money",value="余额",dataType="double", paramType = "query"),
		@ApiImplicitParam(name="accountId",value="账户ID",dataType="long", paramType = "query"),
	  	@ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  	@ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
		@ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="financeId"),
	    @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Finance>> browsePagingFinance(
			@RequestParam(value="money",required=false)Double money,
			@RequestParam(value="accountId",required=false)Long accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="financeId") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			Wrapper<Finance> wrapper=new EntityWrapper<>();
		 	Map<String,Object> map=new HashMap<>();
		 	map.put("money", money);
		 	map.put("account_id", accountId);
		 	wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
			StateResultList<List<Finance>> rl = super.list(pageNum, pageSize, orderName, orderWay,wrapper);
			return rl;
	}
	/**
	 * 财务修改
	 * @return
	 */
	@ApiOperation(value = "财务修改", notes = "财务修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Finance>> updateFinance(
			@ModelAttribute Finance finance,HttpSession session)  {
		finance.setUpdateDate(new Date());
		StateResultList<List<Finance>> u = super.update(finance);
		return u;
	}
	/**
	 * 财务增加
	 * @return 
	 */
	@ApiOperation(value = "财务增加", notes = "财务增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Finance>> addFinance(
			@ModelAttribute Finance finance,
			HttpSession session) {
		finance.setCreateDate(new Date());
		finance.setUpdateDate(new Date());
		StateResultList<List<Finance>> a = super.add(finance);
		return a;
	}
	/**
	 * 财务删除
	 * @return
	 */
	@ApiOperation(value = "财务删除", notes = "财务删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="financeId",value="财务ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Finance>> delFinance(
			@RequestParam("financeId") Long financeId,HttpSession session)  {
		StateResultList<List<Finance>> d = super.delete(financeId);
		return d;
	}
	/**
	 * 财务浏览数量
	 * @return
	 */
	@ApiOperation(value = "财务数量", notes = "财务数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="money",value="余额",dataType="double", paramType = "query"),
			@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query")
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="money",required=false)Double money,
			@RequestParam(value="accountId",required=false)Long accountId,
			HttpSession session)  {
		Wrapper<Finance> wrapper=new EntityWrapper<>();
	 	Map<String,Object> map=new HashMap<>();
	 	map.put("money", money);
	 	map.put("account_id", accountId);
	 	wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
		StateResultList<List<Integer>> c = super.count(wrapper);
		return c;
	}
	/**
	 * 财务单个加载
	 * @return
	 */
	@ApiOperation(value = "财务单个加载", notes = "财务单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="financeId",value="财务ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Finance>> loadFinance(@RequestParam("financeId") Long financeId,HttpSession session)  {
		 StateResultList<List<Finance>> l = super.load(financeId);
		 return l;
	}
	/**
	 * 管理员修改或增加提现密码
	 * @return
	 */
	@ApiOperation(value = "管理员修改或增加交易密码", notes = "管理员修改或增加提现密码")
	@ApiImplicitParams({
			@ApiImplicitParam(name="financeId",value="财务ID",dataType="long", paramType = "query",required=true),
			@ApiImplicitParam(name="password",value="提现密码",dataType="string", paramType = "query",required=true)
	})
	@RequestMapping(value = "/updatePasswordByFinanceId", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody
	StateResult adminUpdatePassword(
			@RequestParam(value="financeId")Long financeId,
			@RequestParam(value="password")String password,
			HttpSession session)  {
		Finance f = financeService.load(financeId);
		f.setPassword(MyDESutil.getMD5(password));
		boolean um = financeService.update(f);
		return ResultUtil.getSR(um);
	}
	/**
	 * 管理员充值
	 * @return
	 */
	@ApiOperation(value = "管理员充值", notes = "管理员充值")
	@ApiImplicitParams({
			@ApiImplicitParam(name="accountId",value="账户ID",dataType="long", paramType = "query",required=true),
			@ApiImplicitParam(name="method",value="方式，1支付宝，2微信,3百度钱包,4Paypal,5网银,6ios内购,7余额",dataType="int", paramType = "query",required=true),
			@ApiImplicitParam(name="money",value="金额",dataType="double", paramType = "query",required=true)
	})
	@RequestMapping(value = "/recharge", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Finance>> rechargeFinance(
			@RequestParam(value="accountId")Long accountId,
			@RequestParam(value="method")Integer method,
			@RequestParam(value="money")Double money,
			HttpSession session) {
		List<Finance> list=new ArrayList<>();
		Finance finance = financeService.rechargeFinance(accountId, method, money);
		list.add(finance);
		return ResultUtil.getSlefSRSuccessList(list);
	}

	/**
	 * 阅读广告支付
	 * @return
	 */
	@ApiOperation(value = "阅读广告支付", notes = "阅读广告支付")
	@ApiImplicitParams({
			@ApiImplicitParam(name="accountId",value="账户ID",dataType="long", paramType = "query",required=true),
			@ApiImplicitParam(name="readAdvertiseId",value="阅读广告ID",dataType="long", paramType = "query",required=true),
			@ApiImplicitParam(name="method",value="方式，1支付宝，2微信,3百度钱包,4Paypal,5网银,6ios内购,7余额",dataType="int", paramType = "query",required=true),
	})
	@RequestMapping(value = "/readAdvertisePayment", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Finance>> readAdvertisePayment(
			@RequestParam(value="accountId")Long accountId,
			@RequestParam(value="readAdvertiseId")Long readAdvertiseId,
			@RequestParam(value="method")Integer method,
			HttpSession session) {
		List<Finance> list=new ArrayList<>();
		Finance finance = financeService.readAdvertisePayment(accountId, readAdvertiseId, method);
		list.add(finance);
		return ResultUtil.getSlefSRSuccessList(list);
	}
}
