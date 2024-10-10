package org.webchat.utils;

import org.webchat.domain.Chat;
import org.webchat.repository.ChatRepoImpl;

import java.util.ArrayList;
import java.util.List;

public class ChatsLaunch {
    public static List<Chat> getChat(List<String> listIdChat) {
        List<Chat> listChat = new ArrayList<>();
        listIdChat.forEach(x -> listChat.add(ChatRepoImpl.getChat(x).get()));
        return listChat;
    }
}
