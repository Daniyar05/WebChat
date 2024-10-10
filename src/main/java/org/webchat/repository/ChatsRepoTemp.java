package org.webchat.repository;

import org.webchat.domain.Chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatsRepoTemp {
    static List<Chat> listChat = new ArrayList<>();

    public static Chat getChat(String idChat) {
        Chat resp = null;
        for (Chat i : listChat) {
            if (Objects.equals(i.getIdChat(), idChat)) {
                resp = i;
                break;
            }
        }
        return resp;
    }

    public static void addChat(Chat chat) {
        listChat.add(chat);
    }

}
