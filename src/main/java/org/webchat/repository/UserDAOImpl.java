package org.webchat.repository;

import org.webchat.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    @Override
    public Optional<User> getUser(String idUser) {
        String userQuery = "SELECT * FROM users WHERE id_user = ?";
        String userChatsQuery = "SELECT id_chat FROM user_chats WHERE id_user = ?";

        try (Connection connection = DatabaseConnection.getConnection();
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
                return Optional.of(new User(idUser,userResultSet.getString(2), userResultSet.getString(4), chats, userResultSet.getString(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    public Optional<User> getUser(String username, String password) {
        String userQuery = "SELECT * FROM users WHERE username = ? AND password_hash = ?";
        String userChatsQuery = "SELECT id_chat FROM user_chats WHERE id_user = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement userStatement = connection.prepareStatement(userQuery);
             PreparedStatement userChatsStatement = connection.prepareStatement(userChatsQuery)) {
            userStatement.setString(1, username);
            userStatement.setString(2, String.valueOf(password.hashCode()));
            ResultSet userResultSet = userStatement.executeQuery();

            if (userResultSet.next()) {
                List<String> chats = new ArrayList<>();
                userChatsStatement.setString(1, userResultSet.getString(1));
                ResultSet userChatsResultSet = userChatsStatement.executeQuery();
                while (userChatsResultSet.next()) {
                    chats.add(userChatsResultSet.getString("id_chat"));
                }
                return Optional.of(new User(userResultSet.getString(1),userResultSet.getString(2), userResultSet.getString(4), chats, userResultSet.getString(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void addUser(User user) {
        String userQuery = "INSERT INTO users (id_user, username, password_hash, email) VALUES (?,?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement userStatement = connection.prepareStatement(userQuery)) {

            userStatement.setString(1, user.getId());
            userStatement.setString(2, user.getUsername());
            userStatement.setString(3, user.getPasswordHash());
            userStatement.setString(4, user.getEmail());

            userStatement.executeUpdate();

            for (String chatId : user.getIdChats()) {
                addUserChat(user.getId(), chatId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUserChat(String userId, String chatId) {
        String userChatQuery = "INSERT INTO user_chats (id_user, id_chat) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement userChatStatement = connection.prepareStatement(userChatQuery)) {

            userChatStatement.setString(1, userId);
            userChatStatement.setString(2, chatId);
            userChatStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
