package org.webchat.repository.DAO;

import org.webchat.domain.User;
import java.util.Optional;

public interface UserDAO {
    Optional<User> getUser(String idUser);
    boolean addUser(User user, String password);

}