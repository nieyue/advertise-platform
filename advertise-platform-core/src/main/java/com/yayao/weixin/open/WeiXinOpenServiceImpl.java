package com.yayao.weixin.open;

import me.chanjar.weixin.open.api.impl.WxOpenInRedisConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenMessageRouter;
import me.chanjar.weixin.open.api.impl.WxOpenServiceImpl;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;

import javax.annotation.PostConstruct;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;

/**
 * 微信开放平台服务
 */
@Service
public class WeiXinOpenServiceImpl extends WxOpenServiceImpl {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 设置微信三方平台的appid
     */
    @Value("${myPugin.weixin.open.componentAppId}")
    private String componentAppId;

    /**
     * 设置微信三方平台的app secret
     */
    @Value("${myPugin.weixin.open.componentSecret}")
    private String componentSecret;

    /**
     * 设置微信三方平台的token
     */
    @Value("${myPugin.weixin.open.componentToken}")
    private String componentToken;

    /**
     * 设置微信三方平台的EncodingAESKey
     */
    @Value("${myPugin.weixin.open.componentAesKey}")
    private String componentAesKey;

    @Value("${myPugin.weixin.redis.host}")
    private String host = Protocol.DEFAULT_HOST;
    @Value("${myPugin.weixin.redis.port}")
    private int port = Protocol.DEFAULT_PORT;
    @Value("${myPugin.weixin.redis.password}")
    private String password;
    @Value("${myPugin.weixin.redis.database}")
    private int database ;
    @Value("${myPugin.weixin.redis.timeout}")
    private int connectionTimeout = Protocol.DEFAULT_TIMEOUT;
    private int soTimeout = Protocol.DEFAULT_TIMEOUT;
    private String clientName;
    private boolean ssl;
    private SSLSocketFactory sslSocketFactory;
    private SSLParameters sslParameters;
    private HostnameVerifier hostnameVerifier;

    WxOpenMessageRouter wxOpenMessageRouter;
    private static JedisPool pool;
    @PostConstruct
    public void init() {
        WxOpenInRedisConfigStorage inRedisConfigStorage = new WxOpenInRedisConfigStorage(getJedisPool());
        inRedisConfigStorage.setComponentAppId(componentAppId);
        inRedisConfigStorage.setComponentAppSecret(componentSecret);
        inRedisConfigStorage.setComponentToken(componentToken);
        inRedisConfigStorage.setComponentAesKey(componentAesKey);
        setWxOpenConfigStorage(inRedisConfigStorage);
        wxOpenMessageRouter = new WxOpenMessageRouter(this);
        wxOpenMessageRouter.rule().handler((wxMpXmlMessage, map, wxMpService, wxSessionManager) -> {
            logger.info("\n接收到 {} 公众号请求消息，内容：{}", wxMpService.getWxMpConfigStorage().getAppId(), wxMpXmlMessage);
            return null;
        }).next();
    }
    public WxOpenMessageRouter getWxOpenMessageRouter(){
        return wxOpenMessageRouter;
    }
    public JedisPool getJedisPool() {
        if (pool == null) {
            synchronized (WeiXinOpenServiceImpl.class) {
                if (pool == null) {
                    GenericObjectPoolConfig pc=new GenericObjectPoolConfig();
                    pool = new JedisPool(pc, host,
                            port, Protocol.DEFAULT_TIMEOUT,
                            Protocol.DEFAULT_TIMEOUT, password,
                            database, clientName,
                            ssl, sslSocketFactory,
                            sslParameters, hostnameVerifier);
                }
            }
        }
        return pool;
    }
}
