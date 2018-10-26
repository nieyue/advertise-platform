package com.yayao.controller;

import com.github.binarywang.wxpay.bean.notify.WxScanPayNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayAppOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayMwebOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayNativeOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayBillResult;
import com.github.binarywang.wxpay.bean.result.WxPayMicropayResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.yayao.comments.IPCountUtil;
import com.yayao.util.DateUtil;
import com.yayao.util.MyQRcode;
import com.yayao.weixin.pay.WeiXinPayServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 微信支付控制类
 * @author yy
 *
 */
@Api(tags={"weiXinPay"},value="微信支付",description="微信支付管理")
@RestController
@RequestMapping("/weiXinPay")
public class WeiXinPayController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Value("${myPugin.projectDomainUrl}")
	String projectDomainUrl;
	@Autowired
	private WeiXinPayServiceImpl weiXinPayServiceImpl;


	@ApiOperation(value = "统一下单", notes = "统一下单")
	@RequestMapping("/unifiedOrder")
	@ApiImplicitParams({
			@ApiImplicitParam(name="orderId",value="订单id",dataType="string", paramType = "query"),
			@ApiImplicitParam(name="type",value="支付类型，1公众号支付，2扫码支付，3app支付,4h5支付，5小程序支付",dataType="string", paramType = "query"),
	})
	//@ResponseBody
	public String unifiedOrder(
			@RequestParam("orderId")String orderId,
			@RequestParam(value="type",required = false,defaultValue = "1")Integer type,
			HttpServletRequest request,
			HttpServletResponse response
	){
		String outtradeno= "ww"+DateUtil.getOrdersTime();
		try {

			//weiXinPayServiceImpl.closeOrder(outtradeno);
			WxPayUnifiedOrderRequest orderRequestrequest=new WxPayUnifiedOrderRequest();
			orderRequestrequest.setBody("测试");
			orderRequestrequest.setNotifyUrl("http://www.baidu.com");
			orderRequestrequest.setTotalFee(1);
			orderRequestrequest.setOpenid(null);
			if(type.equals(1)){
				orderRequestrequest.setTradeType("JSAPI");
				//oRbkdwlukzHf5yLtxjLF_Ujw3i84
				orderRequestrequest.setOpenid(request.getSession().getAttribute("openid").toString());
			}else if(type.equals(2)){
				orderRequestrequest.setTradeType("NATIVE");
				orderRequestrequest.setProductId(outtradeno);
			}else if(type.equals(3)){
				orderRequestrequest.setTradeType("APP");
			}else if(type.equals(4)){
				orderRequestrequest.setTradeType("MWEB");
			}
			orderRequestrequest.setOutTradeNo(outtradeno);
			orderRequestrequest.setSpbillCreateIp(IPCountUtil.getIpAddr(request));
			String result="";
			if(type.equals(1)){
				WxPayMpOrderResult wxPayMpOrderResult = weiXinPayServiceImpl.createOrder(orderRequestrequest);
				//公众号支付
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("appId",wxPayMpOrderResult.getAppId());
				jsonObject.put("timestamp",wxPayMpOrderResult.getTimeStamp());
				jsonObject.put("nonceStr",wxPayMpOrderResult.getNonceStr());
				jsonObject.put("package",wxPayMpOrderResult.getPackageValue());
				jsonObject.put("signType",wxPayMpOrderResult.getSignType());
				jsonObject.put("paySign",wxPayMpOrderResult.getPaySign());
				result=jsonObject.toString();
			}else if(type.equals(2)){
				WxPayUnifiedOrderResult wxPayUnifiedOrderResult=weiXinPayServiceImpl.unifiedOrder(orderRequestrequest);
				//扫码支付
				MyQRcode.createQrcode(wxPayUnifiedOrderResult.getCodeURL(),response.getOutputStream());
			}else if(type.equals(3)){
				//APP支付
				WxPayAppOrderResult wxPayAppOrderResult=weiXinPayServiceImpl.createOrder(orderRequestrequest);
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("appid",wxPayAppOrderResult.getAppId());
				jsonObject.put("timestamp",wxPayAppOrderResult.getTimeStamp());
				jsonObject.put("noncestr",wxPayAppOrderResult.getNonceStr());
				jsonObject.put("package",wxPayAppOrderResult.getPackageValue());
				jsonObject.put("partnerid",wxPayAppOrderResult.getPartnerId());
				jsonObject.put("prepayid",wxPayAppOrderResult.getPrepayId());
				jsonObject.put("sign",wxPayAppOrderResult.getSign());
				result=jsonObject.toString();
			}else if(type.equals(4)){
				//h5支付
				WxPayMwebOrderResult wxPayAppOrderResult = weiXinPayServiceImpl.createOrder(orderRequestrequest);
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("mwebUrl",wxPayAppOrderResult.getMwebUrl());
				result=jsonObject.toString();
			}
			return result;

		} catch (Exception e) {
			logger.error("unifiedOrder", e);
			throw new RuntimeException(e);
		}
	}

}
