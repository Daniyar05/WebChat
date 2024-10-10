package org.webchat.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    final String id;
    List<String> chats;
    String username;
    String email;
    String passwordHash;

    public String getPasswordHash() {
        return passwordHash;
    }

    public User(String id, String username, String email, String passwordHash) {
        this.username = username;
        this.id = id;
        chats = new ArrayList<>();
        this.email=email;
        this.passwordHash = passwordHash;
    }

    public User(String id, String username, String email, List<String> chats, String passwordHash) {
        this.id = id;
        this.chats = chats;
        this.username = username;
        this.email=email;
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
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
