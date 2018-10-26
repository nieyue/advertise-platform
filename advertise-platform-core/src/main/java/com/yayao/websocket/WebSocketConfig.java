package com.yayao.websocket;

import com.yayao.shiro.ShiroConfiguration;
import com.yayao.shiro.ShiroUtil;
import org.apache.catalina.session.StandardSessionFacade;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * websocket配置
 */
@Configuration
public class WebSocketConfig extends ServerEndpointConfig.Configurator {
  /*  @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        *//*如果没有监听器,那么这里获取到的HttpSession是null*//*
        StandardSessionFacade ssf = (StandardSessionFacade) request.getHttpSession();
             if (ssf != null) {
                   HttpSession httpSession = (HttpSession) request.getHttpSession();
                   //关键操作
                   sec.getUserProperties().put("sessionId", httpSession.getId());
                   System.out.println("获取到的SessionID：" + httpSession.getId());
             }
        super.modifyHandshake(sec, request, response);
    }*/

    /**
     * 引入shiro框架下的session，获取session信息
     */
   @Override
   public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
     Session shiroSession = SecurityUtils.getSubject().getSession();
     sec.getUserProperties().put("sessionId", shiroSession.getId());
   }

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){

        return new ServerEndpointExporter();
    }
}
