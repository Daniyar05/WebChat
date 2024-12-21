package org.webchat.utils;

import org.webchat.domain.Chat;
import org.webchat.repository.ChatRepo;
import org.webchat.repository.UserRepo;

public class CreateChatForTwoUser {
    public static String createChat(String idUser1, String idUser2, ChatRepo chatRepo, UserRepo userRepo){
        Chat newChat = new Chat();
        chatRepo.addChat(newChat);
        userRepo.addUserChat(idUser1,newChat.getIdChat());
        userRepo.addUserChat(idUser2,newChat.getIdChat());
        return newChat.getIdChat();
    }
}
