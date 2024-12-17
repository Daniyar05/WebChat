package org.webchat.repository;

import org.webchat.domain.Chat;

import java.util.Optional;

public interface ChatsRepo {
    Optional<Chat> getChat(String idChat);
    boolean addChat(Chat chat);

    boolean renameChat(String idChat, String newUsername);

    boolean deleteChat(String idChat);
}
