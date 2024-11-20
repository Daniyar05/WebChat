package org.webchat.repository;

import org.webchat.domain.Chat;
import org.webchat.domain.Message;

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
}