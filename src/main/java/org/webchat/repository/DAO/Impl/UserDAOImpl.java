package org.webchat.repository.DAO.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.webchat.domain.User;
import org.webchat.db.DatabaseConnection;
import org.webchat.repository.DAO.UserDAO;
import org.webchat.utils.PasswordHasher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
    DatabaseConnection databaseConnection;

    public UserDAOImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public Optional<User> getUserByUsername(String username){
        String userQuery = "SELECT * FROM users WHERE username = ?";
        String userChatsQuery = "SELECT id_chat FROM user_chats WHERE id_user = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement userStatement = connection.prepareStatement(userQuery);
             PreparedStatement userChatsStatement = connection.prepareStatement(userChatsQuery)) {

            userStatement.setString(1, username);
            ResultSet userResultSet = userStatement.executeQuery();

            if (userResultSet.next()) {
                List<String> chats = new ArrayList<>();
                userChatsStatement.setString(1, userResultSet.getString("id_user"));
                ResultSet userChatsResultSet = userChatsStatement.executeQuery();
                while (userChatsResultSet.next()) {
                    chats.add(userChatsResultSet.getString("id_chat"));
                }
                return Optional.of(new User(userResultSet.getString("id_user"),userResultSet.getString("username"), chats));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUser(String idUser) {
        String userQuery = "SELECT * FROM users WHERE id_user = ?";
        String userChatsQuery = "SELECT id_chat FROM user_chats WHERE id_user = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement userStatement = connection.prepareStatement(userQuery);
             PreparedStatement userChatsStatement = connection.prepareStatement(userChatsQuery)) {

            userStatement.setString(1, idUser);
            ResultSet userResultSet = userStatement.executeQuery();

            if (userResultSet.next()) {
                List<String> chats = new ArrayList<>();
                userChatsStatement.setString(1, idUser);
                ResultSet userChatsResultSet = userChatsStatement.executeQuery();
                while (userChatsResultSet.next()) {
                    chats.add(userChatsResultSet.getString("id_chat"));
                }
                return Optional.of(new User(idUser,userResultSet.getString("username"), chats));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public Optional<User> getUser(String username, String password) {
        String userQuery = "SELECT * FROM users WHERE username = ?";
        String userChatsQuery = "SELECT id_chat FROM user_chats WHERE id_user = ?";
        try (Connection connection = databaseConnection.getConnection();
            PreparedStatement userStatement = connection.prepareStatement(userQuery);
            PreparedStatement userChatsStatement = connection.prepareStatement(userChatsQuery)) {
            userStatement.setString(1, username);
            ResultSet userResultSet = userStatement.executeQuery();


            if (userResultSet.next()) {
                if (!PasswordHasher.isTruePassword(password, userResultSet.getString("password_hash"))){
                    return Optional.empty();
                }
                List<String> chats = new ArrayList<>();
                userChatsStatement.setString(1, userResultSet.getString(1));
                ResultSet userChatsResultSet = userChatsStatement.executeQuery();

                while (userChatsResultSet.next()) {
                    chats.add(userChatsResultSet.getString("id_chat"));
                }

                return Optional.of(new User(userResultSet.getString("id_user"),userResultSet.getString("username"), chats));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean hasUsername(String username){
        String userQuery = "SELECT * FROM users WHERE username = ? LIMIT 1";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement userStatement = connection.prepareStatement(userQuery)) {

            userStatement.setString(1, username);
            ResultSet userResultSet = userStatement.executeQuery();

            return userResultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addUser(User user, String password) {
        String userQuery = "INSERT INTO users (id_user, username, password_hash) VALUES (?,?,?)";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement userStatement = connection.prepareStatement(userQuery)) {

            userStatement.setString(1, user.getId());
            userStatement.setString(2, user.getUsername());
            userStatement.setString(3, PasswordHasher.getHashPassword(password));
            userStatement.executeUpdate();

            for (String chatId : user.getIdChats()) {
                addUserChat(user.getId(), chatId);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addUserChat(String userId, String chatId) {
        String userChatQuery = "INSERT INTO user_chats (id_user, id_chat) VALUES (?, ?)";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement userChatStatement = connection.prepareStatement(userChatQuery)) {

            userChatStatement.setString(1, userId);
            userChatStatement.setString(2, chatId);
            userChatStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean replaceUsername(String userId, String newUsername){
        String userQuery = "UPDATE users SET username=? WHERE id_user=?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement userStatement = connection.prepareStatement(userQuery)) {

            userStatement.setString(1, newUsername);
            userStatement.setString(2, userId);
            userStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
