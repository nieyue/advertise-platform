package com.yayao.weixin.pay;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付配置
 */
@Configuration
public class WeiXinPayConfiguration {
    /**
     * 设置微信公众号或者小程序等的appid
     */
    @Value("${myPugin.weixin.pay.appId}")
    private String appId;

    /**
     * 微信支付商户号
     */
    @Value("${myPugin.weixin.pay.mchId}")
    private String mchId;

    /**
     * 微信支付商户密钥
     */
    @Value("${myPugin.weixin.pay.mchKey}")
    private String mchKey;

    /**
     * 服务商模式下的子商户公众账号ID，普通模式请不要配置，请在配置文件中将对应项删除
     */
    @Value("${myPugin.weixin.pay.subAppId}")
    private String subAppId;

    /**
     * 服务商模式下的子商户号，普通模式请不要配置，最好是请在配置文件中将对应项删除
     */
    @Value("${myPugin.weixin.pay.subMchId}")
    private String subMchId;

    /**
     * apiclient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定
     */
    @Value("${myPugin.weixin.pay.keyPath}")
    private String keyPath;

    @Bean
    @ConditionalOnMissingBean
    public WxPayService wxService() {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(StringUtils.trimToNull(appId));
        payConfig.setMchId(StringUtils.trimToNull(mchId));
        payConfig.setMchKey(StringUtils.trimToNull(mchKey));
        payConfig.setSubAppId(StringUtils.trimToNull(subAppId));
        payConfig.setSubMchId(StringUtils.trimToNull(subMchId));
        payConfig.setKeyPath(StringUtils.trimToNull(keyPath));

        // 可以指定是否使用沙箱环境
        payConfig.setUseSandboxEnv(false);

        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }
}
