package org.webchat.repository.Impl;

import org.webchat.db.DatabaseConnection;
import org.webchat.domain.User;
import org.webchat.repository.DAOImpl.UserDAOImpl;

import java.util.Optional;

public class UsersRepoImpl {
    public UsersRepoImpl(DatabaseConnection databaseConnection) {
        USER_DAO = new UserDAOImpl(databaseConnection);
    }

    private final UserDAOImpl USER_DAO;

    public Optional<User> getUser(String idUser) {
        return USER_DAO.getUser(idUser);
    }
    public Optional<User> getUserByUsername(String idUser) {
        return USER_DAO.getUserByUsername(idUser);
    }
    public Optional<User> getUser(String username, String password) {
        return USER_DAO.getUser(username, password);
    }
    public boolean hasUsername(String username) {
        return USER_DAO.hasUsername(username);
    }
    public void addUser(User user, String password) {
        USER_DAO.addUser(user, password);
    }
    public void addUserChat(String userId, String chatId){
        USER_DAO.addUserChat(userId, chatId);
    }
    public boolean replaceUsername(String userId, String newUsername){
        if (hasUsername(newUsername)) return false;
        return USER_DAO.replaceUsername(userId, newUsername);
    }
}
