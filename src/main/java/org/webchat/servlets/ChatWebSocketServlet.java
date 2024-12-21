package org.webchat.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.webchat.config.WebSocketConfigurer;
import org.webchat.domain.Message;
import org.webchat.dto.WebSocketMessage;
import org.webchat.repository.ChatRepo;
import org.webchat.repository.UserRepo;

import java.io.IOException;
import java.util.*;


@Slf4j
@ServerEndpoint(value = "/websocket", configurator = WebSocketConfigurer.class)
public class ChatWebSocketServlet {

    private static final Map<String, Session> sessions = Collections.synchronizedMap(new HashMap<>());

    @Setter
    private static ChatRepo chatRepository;
    @Setter
    private static UserRepo userRepository;

    private ObjectMapper objectMapper = new ObjectMapper();



    @OnOpen
    public void handleOpen(EndpointConfig config, Session session) {
        String userId = (String) config.getUserProperties().get("user");
        sessions.put(userId, session);
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        WebSocketMessage webSocketMessage = null;
        try {
            webSocketMessage = objectMapper.readValue(message, WebSocketMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        chatRepository.addMessage(webSocketMessage.getChatId(), new Message(userRepository.getUser(findUserIdBySession(session)).get(), webSocketMessage.getMessage()));
        List<String> usersInChat = chatRepository.findAllUsersInChat(webSocketMessage.getChatId());
        for(String user : usersInChat) {
            if(sessions.containsKey(user)) {
                sendMessage(sessions.get(user), message);
            }
        }
    }

    @OnClose
    public void handleClose(Session session) {
        sessions.values().remove(session);
    }

    @OnError
    public void handleError(Throwable throwable) {
        log.error(throwable.getMessage());
    }

    private void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String findUserIdBySession(Session session) {
        for (Map.Entry<String, Session> pair : sessions.entrySet()) {
            if (session.equals(pair.getValue())) {
                return pair.getKey();
            }
        }
        return null;
    }

}