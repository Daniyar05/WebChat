package org.webchat.repository;


import org.webchat.domain.Chat;
import org.webchat.domain.Message;

import java.util.Optional;

public class ChatRepoImpl {
    private static final ChatDAOImpl chatDAO = new ChatDAOImpl();

    public static Optional<Chat> getChat(String idChat) {
        return chatDAO.getChat(idChat);
    }

    public static boolean addChat(Chat chat) {
        return chatDAO.addChat(chat);
    }
    public static boolean addMessage(String idChat, Message message){
        return chatDAO.addMessage(idChat, message);
    }
    public static boolean renameChat(String idChat, String newName){
        return chatDAO.renameChat(idChat, newName);
    }
    public static boolean deleteChat(String idChat){
        return (chatDAO.deleteUserComparisonChat(idChat) || chatDAO.deleteChat(idChat));

    }
}
