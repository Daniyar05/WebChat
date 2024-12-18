package org.webchat.repository;

import org.webchat.domain.Chat;
import org.webchat.domain.Message;

import java.util.Optional;

public interface ChatRepo {
    Optional<Chat> getChat(String idChat);
    boolean addChat(Chat chat);

    boolean renameChat(String idChat, String newUsername);

    boolean deleteChat(String idChat);

    boolean addMessage(String chatId, Message message);

    boolean hasUserById(String userId, String chatId);
}
