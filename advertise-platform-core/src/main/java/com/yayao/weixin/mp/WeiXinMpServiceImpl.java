package com.yayao.weixin.mp;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * 微信公众号服务
 */
@Service
public class WeiXinMpServiceImpl  {
    WxMpServiceImpl wxMpService;
    /**
     * 设置微信三方平台的appid
     */
    @Value("${myPugin.weixin.mp.appId}")
    private String appId;

    /**
     * 设置微信三方平台的app secret
     */
    @Value("${myPugin.weixin.mp.secret}")
    private String secret;
    /**
     * 初始化WxMpService
     */
    @PostConstruct
    private void init(){
        wxMpService = new WxMpServiceImpl();
        WxMpInMemoryConfigStorage mcs=new WxMpInMemoryConfigStorage();
        mcs.setAppId(appId);
        mcs.setSecret(secret);
        wxMpService.setWxMpConfigStorage(mcs);
    }
    /**
     * 微信js授权访问
     * @param  url 微信登录获取openid 的服务路径
     * @param  type oauth2网页授权的scope.
     *              1不弹出授权页面，直接跳转，只能获取用户openid.
     *              2弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息.
     *              3手动授权,可获取成员的详细信息,包含手机、邮箱。只适用于企业微信或企业号.
     * @param  state 请求网页原地址
     * @throws WxErrorException
     */
    public String authorize(
            String url,
            Integer type,
            String state
    ){
        String snapi=WxConsts.OAuth2Scope.SNSAPI_BASE;
        switch (type){
            case 1:
                snapi=WxConsts.OAuth2Scope.SNSAPI_BASE;
                break;
            case 2:
                snapi=WxConsts.OAuth2Scope.SNSAPI_USERINFO;
                break;
            case 3:
                snapi=WxConsts.OAuth2Scope.SNSAPI_PRIVATEINFO;
                break;
        }
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, snapi, state);
        return redirectUrl;
    }
    /**
     * 获取openid
     * @param  code 授权码
     * @throws WxErrorException
     */
    public  WxMpUser redirectUrl(
            String code
    ) throws WxErrorException {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
        return wxMpUser;
    }
    /**
     * 微信jssdk 接口
     * @param  url 请求网页原地址
     * @throws WxErrorException
     */
    public WxJsapiSignature initJsApi(
            String url

    ) throws WxErrorException {
        WxJsapiSignature wxJsapiSignature = wxMpService.createJsapiSignature(url);
        return wxJsapiSignature;
    }

}
