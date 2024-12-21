package org.webchat.repository;

import org.webchat.domain.Chat;
import org.webchat.domain.Message;
import org.webchat.domain.User;

import java.util.List;
import java.util.Optional;

public interface ChatRepo {
    Optional<Chat> getChat(String idChat);
    boolean addChat(Chat chat);

    boolean renameChat(String idChat, String newUsername);

    boolean deleteChat(String idChat);

    boolean addMessage(String chatId, Message message);

    boolean hasUserById(String userId, String chatId);

    Optional<List<Message>> getHistoryForChat(String chatId, int offset, int limit);
    List<String> findAllUsersInChat(String chatId);
}
