package org.webchat.domain;

import org.webchat.domain.Chat;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String id;
    private List<String> chats;
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }


    public User(String id, String username) {
        this.username = username;
        this.id = id;
        chats = new ArrayList<>();
    }

    public User(String id, String username, List<String> chats) {
        this.id = id;
        this.chats = chats;
        this.username = username;
    }


    public String getUsername() {
        return username;
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
