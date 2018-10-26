package com.yayao.controller;

import com.yayao.weixin.mp.WeiXinMpServiceImpl;
import com.yayao.weixin.pay.WeiXinPayServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * 微信公众号控制类
 * @author yy
 *
 */
@Api(tags={"weiXinMp"},value="微信公众号",description="微信公众号管理")
@RestController
@RequestMapping("/weiXinMp")
public class WeiXinMpController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Value("${myPugin.projectDomainUrl}")
	String projectDomainUrl;
	@Autowired
	private WeiXinPayServiceImpl weiXinPayServiceImpl;
	@Autowired
	private WeiXinMpServiceImpl weiXinPMpServiceImpl;

	/**
	 * 微信js授权访问
	 *
	 * @return 重定向跳转
	 */
	@ApiOperation(value = "微信js授权访问", notes = "微信js授权访问")
	@RequestMapping(value="/authorize",method={RequestMethod.GET,RequestMethod.POST})
	public  RedirectView authorize(HttpServletRequest request){
		StringBuffer url = request.getRequestURL();
		String baseurl = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
		String state = request.getHeader("Referer");
		return new RedirectView(weiXinPMpServiceImpl.authorize(baseurl+"/weiXinMp/openid",1,state));

	}
	/**
	 * 此链接为  微信js授权访问 跳转后 微信链接跳回的链接
	 * 微信登录获取openid
	 *
	 * @return 重定向跳转
	 */
	@ApiOperation(value = "微信登录获取openid", notes = "微信登录获取openid")
	@ApiImplicitParams({
			@ApiImplicitParam(name="code",value="授权码",dataType="string", paramType = "query"),
			@ApiImplicitParam(name="state",value="请求网页原地址",dataType="string", paramType = "query"),
	})
	@RequestMapping(value="/openid",method={RequestMethod.GET,RequestMethod.POST})
	public  RedirectView openid(
			@RequestParam("code")String code,
			@RequestParam("state")String state,
			HttpSession session) throws IOException, WxErrorException {
		//如果存在session则返回
		if(session.getAttribute("openid")!=null){
			return new RedirectView(state);
		}
		WxMpUser wxMpUser = weiXinPMpServiceImpl.redirectUrl(code);
		session.setAttribute("openid",wxMpUser.getOpenId());
		return new RedirectView(state);

	}
	/**
	 * 微信jssdk 接口
	 *
	 * @return
	 */
	@ApiOperation(value = "微信jssdk 接口", notes = "微信jssdk 接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name="url",value="请求网页原地址",dataType="string", paramType = "query"),
	})
	@RequestMapping(value="/js/connection",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody WxJsapiSignature connectionWeiXin(@RequestParam("url")String url, HttpSession session)
			throws WxErrorException {
		WxJsapiSignature wxJsapiSignature = weiXinPMpServiceImpl.initJsApi(url);
		return wxJsapiSignature;

	}

}
