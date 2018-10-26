package com.yayao.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.FinanceRecord;
import com.yayao.service.FinanceRecordService;
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
 * 财务记录控制类
 * @author yy
 *
 */
@Api(tags={"financeRecord"},value="财务记录",description="财务记录管理")
@RestController
@RequestMapping("/financeRecord")
public class FinanceRecordController extends BaseController<FinanceRecord,Long> {
	@Resource
	private FinanceRecordService financeRecordService;
	
	/**
	 * 财务记录分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "财务记录列表", notes = "财务记录分页浏览")
	@ApiImplicitParams({
		@ApiImplicitParam(name="advertiseId",value="广告Id",dataType="long", paramType = "query"),
		@ApiImplicitParam(name="accountId",value="账户Id",dataType="long", paramType = "query"),
		@ApiImplicitParam(name="status",value="状态，默认1待处理，2成功，3已拒绝",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="method",value="方式，1支付宝，2微信,3百度钱包,4Paypal,5网银",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="type",value="类型，1充值、2消费，3退款，4冻结，5解冻，6收入，7提现",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="transactionNumber",value="交易单号",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		@ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  	@ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  	@ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
		@ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="financeRecordId"),
	    @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<FinanceRecord>> browsePagingFinanceRecord(
			@RequestParam(value="advertiseId",required=false)Long advertiseId,
			@RequestParam(value="accountId",required=false)Long accountId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="method",required=false)Integer method,
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="transactionNumber",required=false)String transactionNumber,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="financeRecordId") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			Wrapper<FinanceRecord> wrapper=new EntityWrapper<>();
		 	Map<String,Object> map=new HashMap<>();
		 	map.put("advertise_id", advertiseId);
		 	map.put("account_id", accountId);
		 	map.put("status", status);
		 	map.put("method", method);
		 	map.put("type", type);
		 	map.put("transaction_number", transactionNumber);
		 	map.put("create_date", createDate);
		 	map.put("update_date", updateDate);
		 	wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
			StateResultList<List<FinanceRecord>> rl = super.list(pageNum, pageSize, orderName, orderWay,wrapper);
			return rl;
	}
	/**
	 * 财务记录修改
	 * @return
	 */
	@ApiOperation(value = "财务记录修改", notes = "财务记录修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<FinanceRecord>> updateFinanceRecord(
			@ModelAttribute FinanceRecord financeRecord,HttpSession session)  {
		financeRecord.setUpdateDate(new Date());
		StateResultList<List<FinanceRecord>> u = super.update(financeRecord);
		return u;
	}
	/**
	 * 财务记录增加
	 * @return 
	 */
	@ApiOperation(value = "财务记录增加", notes = "财务记录增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<FinanceRecord>> addFinanceRecord(
			@ModelAttribute FinanceRecord financeRecord,
			HttpSession session) {
		financeRecord.setCreateDate(new Date());
		financeRecord.setUpdateDate(new Date());
		StateResultList<List<FinanceRecord>> a = super.add(financeRecord);
		return a;
	}
	/**
	 * 财务记录删除
	 * @return
	 */
	@ApiOperation(value = "财务记录删除", notes = "财务记录删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="financeRecordId",value="财务记录ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<FinanceRecord>> delFinanceRecord(
			@RequestParam("financeRecordId") Long financeRecordId,HttpSession session)  {
		StateResultList<List<FinanceRecord>> d = super.delete(financeRecordId);
		return d;
	}
	/**
	 * 财务记录浏览数量
	 * @return
	 */
	@ApiOperation(value = "财务记录数量", notes = "财务记录数量查询")
	@ApiImplicitParams({
		@ApiImplicitParam(name="advertiseId",value="广告Id",dataType="long", paramType = "query"),
		@ApiImplicitParam(name="accountId",value="账户Id",dataType="long", paramType = "query"),
		@ApiImplicitParam(name="status",value="状态，默认1待处理，2成功，3已拒绝",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="method",value="方式，1支付宝，2微信,3百度钱包,4Paypal,5网银",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="type",value="类型，1充值、2消费，3退款，4冻结，5解冻，6收入，7提现",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="transactionNumber",value="交易单号",dataType="int", paramType = "query"),
		@ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
		@ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query")
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> count(
			@RequestParam(value="advertiseId",required=false)Long advertiseId,
			@RequestParam(value="accountId",required=false)Long accountId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="method",required=false)Integer method,
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="transactionNumber",required=false)String transactionNumber,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			HttpSession session)  {
		Wrapper<FinanceRecord> wrapper=new EntityWrapper<>();
	 	Map<String,Object> map=new HashMap<>();
		map.put("advertise_id", advertiseId);
		map.put("account_id", accountId);
		map.put("status", status);
		map.put("method", method);
		map.put("type", type);
		map.put("transaction_number", transactionNumber);
		map.put("create_date", createDate);
		map.put("update_date", updateDate);
	 	wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
		StateResultList<List<Integer>> c = super.count(wrapper);
		return c;
	}
	/**
	 * 财务记录单个加载
	 * @return
	 */
	@ApiOperation(value = "财务记录单个加载", notes = "财务记录单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="financeRecordId",value="财务记录ID",dataType="long", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<FinanceRecord>> loadFinanceRecord(@RequestParam("financeRecordId") Long financeRecordId,HttpSession session)  {
		 StateResultList<List<FinanceRecord>> l = super.load(financeRecordId);
		 return l;
	}
	
}
