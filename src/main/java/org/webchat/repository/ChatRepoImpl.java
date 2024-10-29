package org.webchat.repository;


import org.webchat.domain.Chat;
import org.webchat.domain.Message;

import java.util.Optional;

public class ChatRepoImpl {
    private static final ChatDAOImpl chatDAO = new ChatDAOImpl();

    public static Optional<Chat> getChat(String idChat) {
        return chatDAO.getChat(idChat);
    }

    public static void addChat(Chat chat) {
        chatDAO.addChat(chat);
    }
    public static void addMessage(String idChat, Message message){
        chatDAO.addMessage(idChat, message);
    }
    public static void renameChat(String idChat, String newName){
        chatDAO.renameChat(idChat, newName);
    }
    public static void deleteChat(String idChat){
        chatDAO.deleteChat(idChat);
    }
}
