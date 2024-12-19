package ru.itis.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import lombok.SneakyThrows;
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
        log.warn("Connected user");
        String userId = (String) config.getUserProperties().get("user");
        sessions.put(userId, session);
        log.info("Добавил в мапу пользователя {}", userId);
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
        chatRepository.addMessage(webSocketMessage.getChatId(), new Message(userRepository.getUser(findUserIdBySession(session)).get(), webSocketMessage.getMessage()));
//                .content(new String(webSocketMessage.getMessage()) //.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1)
//                .userFrom(userRepository.getUser(findUserIdBySession(session)).get())
//                .build());
        List<String> usersInChat = chatRepository.findAllUsersInChat(webSocketMessage.getChatId());
        log.warn("Got message for chat {}: {}", webSocketMessage.getChatId(), webSocketMessage.getMessage());
        for(String user : usersInChat) {
            log.info("Смотрю на пользователя {} в чате", user);
            if(sessions.containsKey(user)) {
                log.info("Отправил сообщение пользователю {}: {}", user, message);
                sendMessage(sessions.get(user), message);
            }
        }
    }

    @OnClose
    public void handleClose(Session session) {
        sessions.values().remove(session);
        log.info("Убрал из мапы пользователя");
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
        Set<Map.Entry<String, Session>> entrySet = sessions.entrySet();
        for (Map.Entry<String, Session> pair : entrySet) {
            if (session.equals(pair.getValue())) {
                return pair.getKey();
            }
        }
        return null;
    }

}