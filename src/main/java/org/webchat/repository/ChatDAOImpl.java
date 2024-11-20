package org.webchat.repository;

import org.webchat.domain.Chat;
import org.webchat.domain.Message;
import org.webchat.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChatDAOImpl implements ChatDAO {

    @Override
    public Optional<Chat> getChat(String idChat) {
        String chatQuery = "SELECT * FROM chats WHERE id_chat = ?";
        String messagesQuery = "SELECT * FROM messages WHERE id_chat = ? ORDER BY date";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement chatStatement = connection.prepareStatement(chatQuery);
             PreparedStatement messagesStatement = connection.prepareStatement(messagesQuery)) {

            chatStatement.setString(1, idChat);
            ResultSet chatResultSet = chatStatement.executeQuery();

            if (chatResultSet.next()) {
                List<Message> messages = new ArrayList<>();
                messagesStatement.setString(1, idChat);
                ResultSet messagesResultSet = messagesStatement.executeQuery();
                Optional<User> tempUser;
                while (messagesResultSet.next()) {
                    tempUser = UsersRepoImpl.getUser(messagesResultSet.getString("id_from"));
                    if (tempUser.isPresent()) {
                        Message message = new Message(
                                messagesResultSet.getTimestamp("date"),
                                tempUser.get(),
                                messagesResultSet.getString("content")
                        );
                        messages.add(message);
                    }
                }
                return Optional.of(new Chat(idChat,chatResultSet.getString("name"), messages));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean addChat(Chat chat) {
        String chatQuery = "INSERT INTO chats (id_chat, name) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement chatStatement = connection.prepareStatement(chatQuery)) {

            chatStatement.setString(1, chat.getIdChat());
            chatStatement.setString(2, chat.getName());
            chatStatement.executeUpdate();

            for (Message message : chat.getHistory()) {
                addMessage(chat.getIdChat(), message);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addMessage(String idChat, Message message) {
        String messageQuery = "INSERT INTO messages (id_chat, id_from, content, date) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement messageStatement = connection.prepareStatement(messageQuery)) {

            messageStatement.setString(1, idChat);
            messageStatement.setString(2, message.userFrom().getId());
            messageStatement.setString(3, message.content());
            messageStatement.setTimestamp(4, new Timestamp(message.getData().getTime()));
            messageStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean renameChat(String idChat, String newName){
        String userQuery = "UPDATE chats SET name=? WHERE id_chat=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement userStatement = connection.prepareStatement(userQuery)) {

            userStatement.setString(1, newName);
            userStatement.setString(2, idChat);
            userStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean deleteUserComparisonChat(String idChat){
        String chatQuery = "DELETE FROM user_chats WHERE id_chat=?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement messageStatement = connection.prepareStatement(chatQuery)) {

            messageStatement.setString(1, idChat);
            messageStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean deleteChat(String idChat){
        String chatQuery = "DELETE FROM chats WHERE id_chat=?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement messageStatement = connection.prepareStatement(chatQuery)) {
            deleteMessages(idChat);

            messageStatement.setString(1, idChat);
            messageStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean deleteMessages(String idChat){
        String messageQuery = "DELETE FROM messages WHERE id_chat=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement messageStatement = connection.prepareStatement(messageQuery)) {
            messageStatement.setString(1, idChat);
            messageStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
