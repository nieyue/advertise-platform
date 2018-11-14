package com.yayao.business;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayAppOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayMwebOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.service.WxPayService;
import com.yayao.bean.Payment;
import com.yayao.comments.IPCountUtil;
import com.yayao.service.PaymentService;
import com.yayao.util.Arith;
import com.yayao.util.HttpClientUtil;
import com.yayao.util.MyDom4jUtil;
import com.yayao.util.MyQRcode;
import com.yayao.weixin.pay.WeiXinPayServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;
/**
 * 微信支付工具
 * @author 聂跃
 * @date 2017年9月12日
 */
@Configuration
public class WeiXinBusiness {

        @Resource
        PaymentService paymentService;
        @Resource
        WeiXinPayServiceImpl weiXinPayServiceImpl;
        @Autowired
        private WxPayService wxPayService;

        /**
         * 微信支付
         * @param payment 支付类
         * @param type  微信支付类型，1公众号支付，2扫码支付，3app支付,4h5支付，5小程序支付
         * @param request
         * @return
         * @throws Exception
         */
        public String getPayment(Payment payment,Integer type,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception{

            //存储payment
            boolean b = paymentService.add(payment);
            if(!b){
                return "";
            }
            String result = "";
            try {
                WxPayUnifiedOrderRequest orderRequestrequest = new WxPayUnifiedOrderRequest();
                orderRequestrequest.setBody(payment.getBody());
                orderRequestrequest.setNotifyUrl(payment.getNotifyUrl());
                orderRequestrequest.setTotalFee(new Double(Arith.mul(payment.getMoney(),100)).intValue());
                orderRequestrequest.setOpenid(null);

                orderRequestrequest.setOutTradeNo(payment.getOrderNumber());
                orderRequestrequest.setSpbillCreateIp(IPCountUtil.getIpAddr(request));
                orderRequestrequest.setAttach(payment.getPaymentId().toString());

                if(type.equals(1)){
                     //公众号支付 ，返回参数
                    orderRequestrequest.setTradeType("JSAPI");
                    //oRbkdwlukzHf5yLtxjLF_Ujw3i84
                    orderRequestrequest.setOpenid(request.getSession().getAttribute("openid").toString());
                    WxPayMpOrderResult wxPayMpOrderResult = weiXinPayServiceImpl.createOrder(orderRequestrequest);
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("appId",wxPayMpOrderResult.getAppId());
                    jsonObject.put("timestamp",wxPayMpOrderResult.getTimeStamp());
                    jsonObject.put("nonceStr",wxPayMpOrderResult.getNonceStr());
                    jsonObject.put("package",wxPayMpOrderResult.getPackageValue());
                    jsonObject.put("signType",wxPayMpOrderResult.getSignType());
                    jsonObject.put("paySign",wxPayMpOrderResult.getPaySign());
                    result=jsonObject.toString();
                }else if(type.equals(2)){
                    //扫码支付 ,返回流
                    orderRequestrequest.setTradeType("NATIVE");
                    orderRequestrequest.setProductId(payment.getOrderNumber());
                    WxPayUnifiedOrderResult wxPayUnifiedOrderResult=weiXinPayServiceImpl.unifiedOrder(orderRequestrequest);
                    MyQRcode.createQrcode(wxPayUnifiedOrderResult.getCodeURL(),response.getOutputStream());


                }else if(type.equals(3)){
                    //APP支付，返回参数
                    orderRequestrequest.setTradeType("APP");
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
                    //h5支付，返回参数
                    orderRequestrequest.setTradeType("MWEB");
                    WxPayMwebOrderResult wxPayAppOrderResult = weiXinPayServiceImpl.createOrder(orderRequestrequest);
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("mwebUrl",wxPayAppOrderResult.getMwebUrl());
                    result=jsonObject.toString();
                }
            }catch (Exception e){
                return result;
            }
            return result;

        }



        /**
         * 微信支付回调
         * @param request
         * @return
         */
        public String getNotifyUrl(HttpServletRequest request) {
            boolean signVerified=true;
            Payment payment= new Payment();
            //String paymentId = "100";
            try {
                InputStream inStream = request.getInputStream();
                ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = inStream.read(buffer)) != -1) {
                    outSteam.write(buffer, 0, len);
                }
                outSteam.close();
                inStream.close();
                String result = new String(outSteam.toByteArray(), "utf-8");// 获取微信调用我们notify_url的返回信息
                WxPayOrderNotifyResult map = weiXinPayServiceImpl.parseOrderNotifyResult(result);
                String paymentId =map.getAttach();
                String out_trade_no =map.getOutTradeNo();
                Integer total_fee =map.getTotalFee();
                payment = paymentService.load(Long.valueOf(paymentId));
                //已经处理过了
                if(payment.getStatus()!=1){
                    return "success";
                }
                if (map.getResultCode().equalsIgnoreCase("SUCCESS")) {
                    //订单处理
                    if(!out_trade_no.equals(payment.getOrderNumber())
                            ||!total_fee.equals(Integer.valueOf((int)(payment.getMoney()*100)))){
                        signVerified=false;
                    }

                    if(signVerified &&payment!=null){
                        // TODO 验签成功后
                        //按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
                        // HttpClientUtil.doGet(bookStoreDomainUrl+"/bookOrder/paymentNotifyUrl?auth="+MyDESutil.getMD5("1000")+"&params="+URLEncoder.encode(payment.getBusinessNotifyUrl(),"UTF-8"));//异步回调
                       /* String businessNotifyUrl=payment.getBusinessNotifyUrl();
                        String fenge="&params=";//分割值
                        int fengelength=fenge.length();//分割长度
                        int num=businessNotifyUrl.indexOf(fenge);//分割位置
                        String prefix = businessNotifyUrl.substring(0,num);//分割之前
                        String pas = businessNotifyUrl.substring(num+fengelength);//分割之后

                        String enpas = URLEncoder.encode(pas,"UTF-8");
                        String newBusinessNotifyUrl=prefix+fenge+enpas;
                        String businessResult= HttpClientUtil.doGet(newBusinessNotifyUrl);//异步回调
                        if(JSONObject.fromObject(businessResult).get("code").equals(200)
                                ||JSONObject.fromObject(businessResult).get("code").equals("200")){
                            //支付成功
                            payment.setStatus(2);//成功
                            paymentService.update(payment);
                            //成功
                            return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
                        }*/
                        payment.setStatus(2);//成功
                        paymentService.update(payment);
                        //成功
                        return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
                    }
                    //	}

                }else{
                    payment.setStatus(3);//失败
                    paymentService.update(payment);
                }
            } catch (Exception e) {
                payment.setStatus(4);//异常
                //payment.setBusinessNotifyUrl(e.toString());
                paymentService.update(payment);
            }
            //成功
            return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[FAIL]]></return_msg></xml>";
        }


}
