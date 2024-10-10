package org.webchat.repository;

import org.webchat.domain.Chat;

public interface ChatsRepo {
    Chat getChat(String idChat);
    void addChat(Chat chat);
}
