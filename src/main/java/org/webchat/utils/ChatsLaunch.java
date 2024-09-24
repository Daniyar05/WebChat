package org.webchat.utils;

import org.webchat.domain.Chat;
import org.webchat.repository.ChatsRepo;

import java.util.ArrayList;
import java.util.List;

public class ChatsLaunch {
    public static List<Chat> getChat(List<String> listIdChat){
        List<Chat> listChat = new ArrayList<>();
        listIdChat.stream().forEach(x->listChat.add(ChatsRepo.getChat(x)));
        return listChat;
    }
}
