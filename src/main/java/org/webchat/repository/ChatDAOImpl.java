package org.webchat.repository;

import org.webchat.domain.Chat;
import org.webchat.domain.Message;

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
                while (messagesResultSet.next()) {
                    Message message = new Message(
                            messagesResultSet.getTimestamp("date"),
                            messagesResultSet.getString("id_from"),
                            messagesResultSet.getString("content")
                    );
                    messages.add(message);
                }
                return Optional.of(new Chat(idChat, messages));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void addChat(Chat chat) {
        String chatQuery = "INSERT INTO chats (id_chat) VALUES (?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement chatStatement = connection.prepareStatement(chatQuery)) {

            chatStatement.setString(1, chat.getIdChat());
            chatStatement.executeUpdate();

            for (Message message : chat.getHistory()) {
                addMessage(chat.getIdChat(), message);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMessage(String idChat, Message message) {
        String messageQuery = "INSERT INTO messages (id_chat, id_from, content, date) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement messageStatement = connection.prepareStatement(messageQuery)) {

            messageStatement.setString(1, idChat);
            messageStatement.setString(2, message.idFrom());
            messageStatement.setString(3, message.content());
            messageStatement.setTimestamp(4, new Timestamp(message.getData().getTime()));
            messageStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
