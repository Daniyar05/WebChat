package org.webchat.repository;

import org.webchat.domain.Chat;
import java.util.Optional;

//TODO Дописать interfaces for DAOs
public interface ChatDAO {
    Optional<Chat> getChat(String idChat);
    boolean addChat(Chat chat);
}