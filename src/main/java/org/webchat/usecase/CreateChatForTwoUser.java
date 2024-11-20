package org.webchat.usecase;

import org.webchat.domain.Chat;
import org.webchat.repository.Impl.ChatRepoImpl;
import org.webchat.repository.Impl.UsersRepoImpl;

public class CreateChatForTwoUser {
    public static String createChat(String idUser1, String idUser2){
        Chat newChat = new Chat();
        Root.chatRepo.addChat(newChat);
        Root.usersRepo.addUserChat(idUser1,newChat.getIdChat());
        Root.usersRepo.addUserChat(idUser2,newChat.getIdChat());
        return newChat.getIdChat();
    }
}
