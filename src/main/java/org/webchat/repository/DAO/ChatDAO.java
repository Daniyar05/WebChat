package org.webchat.repository.DAO;

import org.webchat.domain.Chat;
import org.webchat.domain.Message;
import org.webchat.domain.User;

import java.util.List;
import java.util.Optional;

//TODO Дописать interfaces for DAOs
public interface ChatDAO {
    Optional<Chat> getChat(String idChat);
    boolean addChat(Chat chat);
    boolean addMessage(String idChat, Message message);
    boolean renameChat(String idChat, String newName);
    boolean deleteChat(String idChat);
    boolean deleteMessages(String idChat);
    boolean deleteUserComparisonChat(String idChat);


    boolean hasUserById(String userId, String chatId);

    List<String> getUsersInChatById(String chatId);
}