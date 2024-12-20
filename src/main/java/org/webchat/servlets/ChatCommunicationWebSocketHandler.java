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
@ServerEndpoint(value = "/ws", configurator = WebSocketConfigurer.class)
public class ChatCommunicationWebSocketHandler {

    private static Map<String, Session> sessions = Collections.synchronizedMap(new HashMap<>());

    private ObjectMapper objectMapper = new ObjectMapper();

    @Setter
    public static ChatRepo chatRepository;
    @Setter
    public static UserRepo userRepository;

    @OnOpen
    public void handleOpen(EndpointConfig config, Session session) {
        String user = (String) config.getUserProperties().get("user");
        log.info("Подключился пользователь {}", user);
        sessions.put(user, session);
    }

    @OnClose
    public void handleClose(Session session) {
        log.info("Отключился пользователь");
        sessions.values().remove(session);
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        log.info("Получил сообщение {}", message);
        WebSocketMessage webSocketMessage = null;
        try {
            webSocketMessage = objectMapper.readValue(message, WebSocketMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String userId = findUserIdBySession(session);
        chatRepository.addMessage(webSocketMessage.getChatId(), new Message(userRepository.getUser(userId).get(),webSocketMessage.getMessage()));
////                .chatId(webSocketMessage.getChatId())
//                .userFrom(userRepository.getUser(userId).get())
//                .content(webSocketMessage.getMessage())
//                .build());

        List<String> usersId = chatRepository.findAllUsersInChat(webSocketMessage.getChatId());
        for(String user : usersId) {
            log.info("Проверяю пользователя {}", user);
            if(sessions.containsKey(user)) {
                log.info("Пользователь {} онлайн, отправляю сообщение", user);
                sendMessage(sessions.get(user), message);
            }
        }
    }

    @OnError
    public void handleException(Throwable throwable) {
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
        Set<Map.Entry<String, Session>> entrySet = sessions.entrySet();
        for(Map.Entry<String, Session> pair : entrySet) {
            if(session.equals(pair.getValue())) {
                return pair.getKey();
            }
        }
        return null;
    }
}