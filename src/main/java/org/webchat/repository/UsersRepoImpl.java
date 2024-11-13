package org.webchat.repository;

import org.webchat.domain.User;

import java.util.Optional;

public class UsersRepoImpl {
    private static final UserDAOImpl USER_DAO = new UserDAOImpl();

    public static Optional<User> getUser(String idUser) {
        return USER_DAO.getUser(idUser);
    }
    public static Optional<User> getUser(String username, String password) {
        return USER_DAO.getUser(username, password);
    }
    public static boolean hasUsername(String username) {
        return USER_DAO.hasUsername(username);
    }
    public static void addUser(User user, String password) {
        USER_DAO.addUser(user, password);
    }
    public static void addUserChat(String userId, String chatId){
        USER_DAO.addUserChat(userId, chatId);
    }
    public static void replaceUsername(String userId, String newUsername){
        USER_DAO.replaceUsername(userId, newUsername);
    }
}
