package org.webchat.repository.DAO.Impl;

import org.webchat.domain.Chat;
import org.webchat.domain.Message;
import org.webchat.domain.User;
import org.webchat.repository.DAO.ChatDAO;
import org.webchat.db.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChatDAOImpl implements ChatDAO {
    DatabaseConnection databaseConnection;

    public ChatDAOImpl(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public Optional<Chat> getChat(String idChat) {
        String chatQuery = "SELECT * FROM chats WHERE id_chat = ?";
        String messagesQuery = "SELECT * FROM messages WHERE id_chat = ? ORDER BY date";

        try (Connection connection = databaseConnection.getConnection();
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
                    tempUser = getUser(messagesResultSet.getString("id_from"));
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

        try (Connection connection = databaseConnection.getConnection();
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
        try (Connection connection = databaseConnection.getConnection();
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
        try (Connection connection = databaseConnection.getConnection();
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
        try (Connection connection = databaseConnection.getConnection();
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

        try (Connection connection = databaseConnection.getConnection();
            PreparedStatement messageStatement = connection.prepareStatement(chatQuery)) {
//            deleteMessages(idChat);

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

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement messageStatement = connection.prepareStatement(messageQuery)) {
            messageStatement.setString(1, idChat);
            messageStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasUserById(String userId, String chatId){
        String userQuery = "SELECT * FROM user_chats WHERE userId = ? AND chatId = ? LIMIT 1";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement userStatement = connection.prepareStatement(userQuery)) {

            userStatement.setString(1, userId);
            userStatement.setString(2, chatId);
            ResultSet userResultSet = userStatement.executeQuery();

            return userResultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<String> getUsersInChatById(String chatId) {
        String query = "SELECT id_user FROM user_chats WHERE id_chat = ?";
        List<String> users = new ArrayList<>();

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, chatId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                users.add(resultSet.getString("id_user"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    @Override
    public void deleteOldChats(){
        String query = "DELETE FROM chats WHERE created_at < NOW() - INTERVAL '1 day' ;";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Optional<User> getUser(String idUser) {
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


}
