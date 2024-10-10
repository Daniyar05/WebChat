package org.webchat.repository;

import org.webchat.domain.User;
import java.util.Optional;

public interface UserDAO {
    Optional<User> getUser(String idUser);
    void addUser(User user);
}