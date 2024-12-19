package org.webchat.config;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;
import jakarta.websocket.HandshakeResponse;


public class WebSocketConfigurer extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        String user = (String) httpSession.getAttribute("userId");
        System.out.println(user);
        sec.getUserProperties().put("user", user);
    }
}