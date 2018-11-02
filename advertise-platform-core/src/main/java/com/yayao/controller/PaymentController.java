package com.yayao.controller;

import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.Payment;
import com.yayao.business.AlipayBusiness;
import com.yayao.business.WeiXinBusiness;
import com.yayao.service.PaymentService;
import com.yayao.util.DateUtil;
import com.yayao.util.MyDom4jUtil;
import com.yayao.util.ResultUtil;
import com.yayao.util.StateResultList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;



/**
 * 支付控制类
 * @author yy
 *
 */
@Api(tags={"payment"},value="支付",description="支付管理")
@RestController
@RequestMapping("/payment")
public class PaymentController extends BaseController<Payment,Long>{
	@Value("${myPugin.projectDomainUrl}")
	String projectDomainUrl;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private AlipayBusiness alipayBusiness;
	@Autowired
	private WeiXinBusiness weiXinBusiness;

	
	/**
	 * 支付分页浏览
	 * @param orderName 支付排序数据库字段
	 * @param orderWay 支付排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "支付列表", notes = "支付分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="orderNumber",value="订单号",dataType="string", paramType = "query"),
	  @ApiImplicitParam(name="type",value="支付类型，1支付宝，2微信,3百度钱包,4Paypal,5网银",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="businessType",value="业务类型，1充值，2提现，3退款",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="businessId",value="业务ID",dataType="long", paramType = "query"),
	  @ApiImplicitParam(name="accountId",value="账户ID",dataType="long", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="status",value="状态，1已下单-未支付，2支付成功，3支付失败,4异常",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="payment_id"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody
	StateResultList<List<Payment>> browsePagingPayment(
			@RequestParam(value="orderNumber",required=false)String orderNumber,
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="businessType",required=false)Integer businessType,
			@RequestParam(value="businessId",required=false)Long businessId,
			@RequestParam(value="accountId",required=false)Long accountId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="paymentId") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {

		Wrapper<Payment> wrapper=new EntityWrapper<>();
		Map<String,Object> map=new HashMap<>();
		map.put("order_number", orderNumber);
		map.put("type", type);
		map.put("business_type", businessType);
		map.put("business_id", businessId);
		map.put("account_id", accountId);
		map.put("create_date", createDate);
		map.put("update_date", updateDate);
		map.put("status", status);
		wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
		StateResultList<List<Payment>> rl = super.list(pageNum, pageSize, orderName, orderWay,wrapper);
		return rl;
	}
	/**
	 * 支付修改
	 * @return
	 */
	@ApiOperation(value = "支付修改", notes = "支付修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody
	StateResultList<List<Payment>> updatePayment(@ModelAttribute Payment payment, HttpSession session)  {
		StateResultList<List<Payment>> u= super.update(payment);
		return u;
	}
	/**
	 * 阿里云支付查询
	 * @return
	 * @throws AlipayApiException 
	 */
	@ApiOperation(value = "阿里云支付查询", notes = "阿里云支付查询")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="orderNumber",value="订单号",dataType="string", paramType = "query",required=true)
	 	  })
	@RequestMapping(value = "/alipayTradeQuery", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<String>> alipayAlipayTradeQuery(
			@RequestParam(value="orderNumber") String orderNumber,
			HttpSession session) throws AlipayApiException  {
		String body = alipayBusiness.getAlipayTradeQuery(orderNumber);
		List<String> ls=new ArrayList<String>();
		if(!body.equals("")&&body!=null){
			ls.add(body);
			return ResultUtil.getSlefSRSuccessList(ls);
		}
		return ResultUtil.getSlefSRFailList(ls);
	}
	@ApiOperation(value = "阿里云支付统一下单", notes = "阿里云支付统一下单")
	@RequestMapping("/alipayUnifiedOrder")
	@ApiImplicitParams({
			@ApiImplicitParam(name="orderId",value="订单id",dataType="string", paramType = "query"),
			@ApiImplicitParam(name="type",value="阿里云支付类型，1当面付，2app支付，3手机网站,4电脑网站",dataType="string", paramType = "query"),
	})
	//@ResponseBody
	public String alipayUnifiedOrder(
			@RequestParam("orderId")String orderId,
			@RequestParam(value="type",required = false,defaultValue = "1")Integer type,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception {
		Payment payment=new Payment();
		payment.setSubject("测试");
		payment.setBody("测试");
		String outtradeno = "ww" + DateUtil.getOrdersTime();
		payment.setOrderNumber(outtradeno);
		payment.setMoney(0.01);
		payment.setStatus(1);//已下单
		payment.setType(2);//支付类型，1支付宝，2微信,3百度钱包,4Paypal,5网银
		payment.setBusinessType(1);//业务类型，1充值，2提现，3退款
		payment.setNotifyUrl("/payment/alipayNotifyUrl");//支付回调
		payment.setBusinessNotifyUrl("http://www.baidu.com");//站内回调
		payment.setCreateDate(new Date());
		payment.setUpdateDate(new Date());
		payment.setAccountId(1000l);
		String result = weiXinBusiness.getPayment(payment, type, request, response);
		return result;

	}
	/**
	 * 阿里云支付回调
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "阿里云支付回调", notes = "阿里云支付回调")
	@RequestMapping(value = "/alipayNotifyUrl", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String alipayNotifyUrl(
			HttpServletRequest request, HttpSession session) {
		String pm = alipayBusiness.getNotifyUrl(request);
		return pm;
	}

	@ApiOperation(value = "微信支付统一下单", notes = "微信支付统一下单")
	@RequestMapping("/weXinUnifiedOrder")
	@ApiImplicitParams({
			@ApiImplicitParam(name="orderId",value="订单id",dataType="string", paramType = "query"),
			@ApiImplicitParam(name="type",value="微信支付类型，1公众号支付，2扫码支付，3app支付,4h5支付，5小程序支付",dataType="string", paramType = "query"),
	})
	//@ResponseBody
	public String weXinUnifiedOrder(
			@RequestParam("orderId")String orderId,
			@RequestParam(value="type",required = false,defaultValue = "1")Integer type,
			HttpServletRequest request,
			HttpServletResponse response
	) throws Exception {
		Payment payment=new Payment();
		payment.setSubject("测试");
		payment.setBody("测试");
		String outtradeno = "ww" + DateUtil.getOrdersTime();
		payment.setOrderNumber(outtradeno);
		payment.setMoney(0.01);
		payment.setStatus(1);//已下单
		payment.setType(2);//支付类型，1支付宝，2微信,3百度钱包,4Paypal,5网银
		payment.setBusinessType(1);//业务类型，1充值，2提现，3退款
		payment.setNotifyUrl("/payment/weiXinPayNotifyUrl");//支付回调
		payment.setBusinessNotifyUrl("http://www.baidu.com");//站内回调
		payment.setCreateDate(new Date());
		payment.setUpdateDate(new Date());
		payment.setAccountId(1000l);
		String result = weiXinBusiness.getPayment(payment, type, request, response);
		return result;

	}
	/**
	 * 微信支付回调
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "微信支付回调", notes = "微信支付回调")
	@RequestMapping(value = "/weiXinPayNotifyUrl", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String weiXinPayNotifyUrl(
			HttpServletRequest request, HttpSession session) {
		String pm = weiXinBusiness.getNotifyUrl(request);
		return pm;
	}

	/**
	 * 支付增加
	 * @return 
	 */
	@ApiOperation(value = "支付增加", notes = "支付增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Payment>> addPayment(@RequestBody Payment payment, HttpSession session) {
		StateResultList<List<Payment>> a = super.add(payment);
		return a;
	}
	/**
	 * 支付删除
	 * @return
	 */
	@ApiOperation(value = "支付删除", notes = "支付删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="paymentId",value="支付ID",dataType="long", paramType = "query",required=true)
		 	  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Payment>>  delPayment(@RequestParam("paymentId") Long paymentId,HttpSession session)  {
		StateResultList<List<Payment>> d = super.delete(paymentId);
		return d;
	}
	/**
	 * 支付浏览数量
	 * @return
	 */
	@ApiOperation(value = "支付浏览数量", notes = "支付浏览数量")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="orderNumber",value="订单号",dataType="string", paramType = "query"),
	  @ApiImplicitParam(name="type",value="支付类型，1支付宝，2微信,3百度钱包,4Paypal,5网银",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="businessType",value="业务类型，1充值，2提现，3退款",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="businessId",value="业务ID",dataType="long", paramType = "query"),
	  @ApiImplicitParam(name="accountId",value="账户ID",dataType="long", paramType = "query"),
	  @ApiImplicitParam(name="createDate",value="创建时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="updateDate",value="更新时间",dataType="date-time", paramType = "query"),
	  @ApiImplicitParam(name="status",value="状态，1已下单-未支付，2支付成功，3支付失败,4异常",dataType="int", paramType = "query")
	  })
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> countAll(
			@RequestParam(value="orderNumber",required=false)String orderNumber,
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="businessType",required=false)Integer businessType,
			@RequestParam(value="businessId",required=false)Long businessId,
			@RequestParam(value="accountId",required=false)Long accountId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="status",required=false)Integer status,
			HttpSession session)  {
		Wrapper<Payment> wrapper=new EntityWrapper<>();
		Map<String,Object> map=new HashMap<>();
		map.put("order_number", orderNumber);
		map.put("type", type);
		map.put("business_type", businessType);
		map.put("business_id", businessId);
		map.put("account_id", accountId);
		map.put("create_date", createDate);
		map.put("update_date", updateDate);
		map.put("status", status);
		wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
		StateResultList<List<Integer>> c = super.count(wrapper);
		return c;
	}
	/**
	 * 支付单个加载
	 * @return
	 */
	@ApiOperation(value = "支付单个加载", notes = "支付单个加载")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="paymentId",value="支付ID",dataType="long", paramType="query",required=true)
	 	  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Payment>> loadPayment(@RequestParam("paymentId") Long paymentId,HttpSession session)  {
		StateResultList<List<Payment>> l = super.load(paymentId);
		return l;
	}
	
}
