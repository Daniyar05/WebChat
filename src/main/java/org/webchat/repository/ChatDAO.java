package org.webchat.repository;

import org.webchat.domain.Chat;
import java.util.Optional;

public interface ChatDAO {
    Optional<Chat> getChat(String idChat);
    void addChat(Chat chat);
}