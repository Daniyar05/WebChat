package org.webchat.data;

import org.webchat.domain.Chat;

import java.util.ArrayList;
import java.util.List;

public class UserNew {
    final String id;
    List<String> chats;
    String username;

    public void setUsername(String username) {
        this.username = username;
    }


    public UserNew(String id, String username) {
        this.username = username;
        this.id = id;
        chats = new ArrayList<>();
    }

    public UserNew(String id, String username, List<String> chats, String passwordHash) {
        this.id = id;
        this.chats = chats;
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    public List<String> getChats() {
        return chats;
    }

    public void addChat(Chat chat) {
        chats.add(chat.getIdChat());
    }

    public String getId() {
        return id;
    }

    public List<String> getIdChats() {
        return chats;
    }

}
