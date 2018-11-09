package com.yayao.controller;

import com.yayao.bean.Account;
import com.yayao.bean.Media;
import com.yayao.weixin.open.WeiXinOpenServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpDataCubeService;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeArticleResult;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.open.bean.message.WxOpenXmlMessage;
import me.chanjar.weixin.open.bean.result.WxOpenAuthorizerInfoResult;
import me.chanjar.weixin.open.bean.result.WxOpenQueryAuthResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;


/**
 * 微信开放平台控制类
 * @author yy
 *
 */
@Api(tags={"weiXinOpen"},value="微信开放平台",description="微信开放平台管理")
@RestController
@RequestMapping("/weiXinOpen")
public class WeiXinOpenController  {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Value("${myPugin.projectDomainUrl}")
	String projectDomainUrl;
	@Autowired
	private WeiXinOpenServiceImpl weiXinOpenServiceImpl;

	@ApiOperation(value = "授权事件接收URL", notes = "授权事件接收URL")
	@RequestMapping("/receive_ticket")
	public Object receiveTicket(
			@RequestBody(required = false) String requestBody,
			@RequestParam("timestamp") String timestamp,
			@RequestParam("nonce") String nonce,
			@RequestParam("signature") String signature,
			@RequestParam(name = "encrypt_type", required = false) String encType,
			@RequestParam(name = "msg_signature", required = false) String msgSignature) {
		this.logger.info(
				"\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
						+ " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
				signature, encType, msgSignature, timestamp, nonce, requestBody);

		if (!StringUtils.equalsIgnoreCase("aes", encType)
				|| !weiXinOpenServiceImpl.getWxOpenComponentService().checkSignature(timestamp, nonce, signature)) {
			throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
		}

		// aes加密的消息
		WxOpenXmlMessage inMessage = WxOpenXmlMessage.fromEncryptedXml(requestBody,
				weiXinOpenServiceImpl.getWxOpenConfigStorage(), timestamp, nonce, msgSignature);
		this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
		try {
			String out = weiXinOpenServiceImpl.getWxOpenComponentService().route(inMessage);
			this.logger.debug("\n组装回复信息：{}", out);
		} catch (WxErrorException e) {
			this.logger.error("receive_ticket", e);
		}


		return "success";
	}
	@ApiOperation(value = "消息与事件接收URL", notes = "消息与事件接收URL")
	@RequestMapping("{appId}/callback")
	public Object callback(@RequestBody(required = false) String requestBody,
						   @PathVariable("appId") String appId,
						   @RequestParam("signature") String signature,
						   @RequestParam("timestamp") String timestamp,
						   @RequestParam("nonce") String nonce,
						   @RequestParam("openid") String openid,
						   @RequestParam("encrypt_type") String encType,
						   @RequestParam("msg_signature") String msgSignature) {
		this.logger.info(
				"\n接收微信请求：[appId=[{}], openid=[{}], signature=[{}], encType=[{}], msgSignature=[{}],"
						+ " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
				appId, openid, signature, encType, msgSignature, timestamp, nonce, requestBody);
		if (!StringUtils.equalsIgnoreCase("aes", encType)
				|| !weiXinOpenServiceImpl.getWxOpenComponentService().checkSignature(timestamp, nonce, signature)) {
			throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
		}

		String out = "";
		// aes加密的消息
		WxMpXmlMessage inMessage = WxOpenXmlMessage.fromEncryptedMpXml(requestBody,
				weiXinOpenServiceImpl.getWxOpenConfigStorage(), timestamp, nonce, msgSignature);
		this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
		// 全网发布测试用例
		if (StringUtils.equalsAnyIgnoreCase(appId, "wxd101a85aa106f53e", "wx570bc396a51b8ff8")) {
			try {
				if (StringUtils.equals(inMessage.getMsgType(), "text")) {
					if (StringUtils.equals(inMessage.getContent(), "TESTCOMPONENT_MSG_TYPE_TEXT")) {
						out = WxOpenXmlMessage.wxMpOutXmlMessageToEncryptedXml(
								WxMpXmlOutMessage.TEXT().content("TESTCOMPONENT_MSG_TYPE_TEXT_callback")
										.fromUser(inMessage.getToUser())
										.toUser(inMessage.getFromUser())
										.build(),
								weiXinOpenServiceImpl.getWxOpenConfigStorage()
						);
					} else if (StringUtils.startsWith(inMessage.getContent(), "QUERY_AUTH_CODE:")) {
						String msg = inMessage.getContent().replace("QUERY_AUTH_CODE:", "") + "_from_api";
						WxMpKefuMessage kefuMessage = WxMpKefuMessage.TEXT().content(msg).toUser(inMessage.getFromUser()).build();
						weiXinOpenServiceImpl.getWxOpenComponentService().getWxMpServiceByAppid(appId).getKefuService().sendKefuMessage(kefuMessage);
					}
				} else if (StringUtils.equals(inMessage.getMsgType(), "event")) {
					WxMpKefuMessage kefuMessage = WxMpKefuMessage.TEXT().content(inMessage.getEvent() + "from_callback").toUser(inMessage.getFromUser()).build();
					weiXinOpenServiceImpl.getWxOpenComponentService().getWxMpServiceByAppid(appId).getKefuService().sendKefuMessage(kefuMessage);
				}
			} catch (WxErrorException e) {
				logger.error("callback", e);
			}
		}else{
			WxMpXmlOutMessage outMessage = weiXinOpenServiceImpl.getWxOpenMessageRouter().route(inMessage, appId);
			if(outMessage != null){
				out = WxOpenXmlMessage.wxMpOutXmlMessageToEncryptedXml(outMessage, weiXinOpenServiceImpl.getWxOpenConfigStorage());
			}
		}
		return out;
	}

	@ApiOperation(value = "跳转授权url", notes = "跳转授权url")
	@RequestMapping("/auth/goto_auth_url")
	public void gotoPreAuthUrl(HttpServletRequest request,
							   @RequestParam("accountId") Long accountId,
							   HttpServletResponse response){
		//String host = request.getHeader("host");
		//确认当前账户
		Account sessionAccount=(Account)request.getSession().getAttribute("account");
		if(sessionAccount==null||(!sessionAccount.getAccountId().equals(accountId))){
			return;
		}
		String url = projectDomainUrl+"/weiXinOpen/auth/jump?accountId="+accountId;
		try {
			url = weiXinOpenServiceImpl.getWxOpenComponentService().getPreAuthUrl(url);
			response.sendRedirect(url);
		} catch (WxErrorException | IOException e) {
			logger.error("gotoPreAuthUrl", e);
			throw new RuntimeException(e);
		}
	}
	@ApiOperation(value = "授权后回调URI，得到授权码（authorization_code）和过期时间", notes = "授权后回调URI，得到授权码（authorization_code）和过期时间")
	@RequestMapping("/auth/jump")
	@ResponseBody
	public WxOpenQueryAuthResult jump(
			@RequestParam("auth_code") String authorizationCode,
			@RequestParam("accountId") Long accountId
	){
		try {
			WxOpenQueryAuthResult queryAuthResult = weiXinOpenServiceImpl.getWxOpenComponentService().getQueryAuth(authorizationCode);
			logger.info("getQueryAuth", queryAuthResult);
			System.out.println(accountId);
			//获取用户信息
			WxOpenAuthorizerInfoResult ai = weiXinOpenServiceImpl.getWxOpenComponentService().
					getAuthorizerInfo(queryAuthResult.getAuthorizationInfo().
							getAuthorizerAppid());
			//授权成功后添加媒体信息
			Media media =new Media();
			return queryAuthResult;
		} catch (WxErrorException e) {
			logger.error("gotoPreAuthUrl", e);
			throw new RuntimeException(e);
		}
	}
	@ApiOperation(value = "获取公众号账户信息", notes = "获取公众号账户信息")
	@RequestMapping("/get_authorizer_info")
	@ResponseBody
	public WxOpenAuthorizerInfoResult getAuthorizerInfo(@RequestParam String appId){
		try {
			return weiXinOpenServiceImpl.getWxOpenComponentService().getAuthorizerInfo(appId);
		} catch (WxErrorException e) {
			logger.error("getAuthorizerInfo", e);
			throw new RuntimeException(e);
		}
	}
	@ApiOperation(value = "获取图文群发每日数据", notes = "获取图文群发每日数据")
	@RequestMapping("/getarticlesummary")
	@ResponseBody
	public List<WxDataCubeArticleResult> getarticlesummary(
			@RequestParam String appId
	){
		try {
			WxMpService wxMpService=weiXinOpenServiceImpl.getWxOpenComponentService().getWxMpServiceByAppid(appId);
			System.err.println(wxMpService.getAccessToken());

			WxMpDataCubeService data = wxMpService.getDataCubeService();
			return data.getArticleSummary(new Date("2018/8/2"),new Date("2018/8/2"));
		} catch (WxErrorException e) {
			logger.error("getarticlesummary", e);
			throw new RuntimeException(e);
		}
	}
}
