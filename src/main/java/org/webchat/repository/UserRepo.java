package org.webchat.repository;

import org.webchat.domain.User;

import java.util.Optional;

public interface UserRepo {
    Optional<User> getUser(String thisUserId);

    void addUserChat(String userId, String idChat);

    Optional<User> getUser(String username, String password);

    boolean hasUsername(String username);

    void addUser(User newUser, String password);

    boolean replaceUsername(String userId, String newUsername);
}
