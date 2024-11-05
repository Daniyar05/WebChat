package org.webchat.usecase;

import org.webchat.domain.Chat;
import org.webchat.repository.ChatRepoImpl;
import org.webchat.repository.UsersRepoImpl;

public class CreateChatForTwoUser {
    public static void createChat(String idUser1, String idUser2){

        Chat newChat = new Chat();
        UsersRepoImpl.addUserChat(idUser1,newChat.getIdChat());
        UsersRepoImpl.addUserChat(idUser2,newChat.getIdChat());
        ChatRepoImpl.addChat(newChat);

    }
}
