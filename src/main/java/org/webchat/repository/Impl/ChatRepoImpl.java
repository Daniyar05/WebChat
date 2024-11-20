package org.webchat.repository.Impl;


import org.webchat.db.DatabaseConnection;
import org.webchat.domain.Chat;
import org.webchat.domain.Message;
import org.webchat.repository.DAOImpl.ChatDAOImpl;

import java.util.Optional;

public class ChatRepoImpl {
    private final ChatDAOImpl chatDAO;

    public ChatRepoImpl(DatabaseConnection databaseConnection) {
        chatDAO=new ChatDAOImpl(databaseConnection);
    }

    public Optional<Chat> getChat(String idChat) {
        return chatDAO.getChat(idChat);
    }

    public boolean addChat(Chat chat) {
        return chatDAO.addChat(chat);
    }
    public boolean addMessage(String idChat, Message message){
        return chatDAO.addMessage(idChat, message);
    }
    public boolean renameChat(String idChat, String newName){
        return chatDAO.renameChat(idChat, newName);
    }
    public boolean deleteChat(String idChat){
        return (chatDAO.deleteUserComparisonChat(idChat) || chatDAO.deleteChat(idChat));

    }
}
