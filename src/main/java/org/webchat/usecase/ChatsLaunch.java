package org.webchat.usecase;

import org.webchat.domain.Chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChatsLaunch {
    public static List<Chat> getChat(List<String> listIdChat) {
        List<Chat> listChat = new ArrayList<>();
        Optional<Chat> temp;
        for (String s : listIdChat) {
            temp = Root.chatRepo.getChat(s);
            temp.ifPresent(listChat::add);
        }
        return listChat;
    }
}
