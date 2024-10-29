package org.webchat.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    final String id;
    List<String> chats;
    String username;
    String passwordHash;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public User(String id, String username, String passwordHash) {
        this.username = username;
        this.id = id;
        chats = new ArrayList<>();
        this.passwordHash = passwordHash;
    }

    public User(String id, String username, List<String> chats, String passwordHash) {
        this.id = id;
        this.chats = chats;
        this.username = username;
        this.passwordHash = passwordHash;
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