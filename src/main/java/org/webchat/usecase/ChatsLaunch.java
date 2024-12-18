package org.webchat.usecase;

import org.webchat.domain.Chat;
import org.webchat.repository.ChatRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChatsLaunch {
    public static List<Chat> getChat(List<String> listIdChat, ChatRepo chatRepo) {
        List<Chat> listChat = new ArrayList<>();
        Optional<Chat> temp;
        for (String s : listIdChat) {
            temp = chatRepo.getChat(s);
            temp.ifPresent(listChat::add);
        }
        return listChat;
    }
}
