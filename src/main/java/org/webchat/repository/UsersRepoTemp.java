package org.webchat.repository;

import org.webchat.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsersRepoTemp {
    static List<User> listUsers = new ArrayList<>();

    public static User getUser(String idUser) {
        User resp = null;
        for (User i : listUsers) {
            if (Objects.equals(i.getId(), idUser)) {
                resp = i;
                break;
            }
        }
        return resp;
    }

    public static void addUser(User user) {
        listUsers.add(user);
    }

}
