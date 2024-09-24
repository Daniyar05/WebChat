package org.webchat.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class User {
    final String id;
    List<String> chats;

    public User(String id){
        this.id = id;
        chats = new ArrayList<>();
    }
    public void addChat(Chat chat){
        chats.add(chat.getIdChat());
    }

    public String getId() {
        return id;
    }

    public List<String> getIdChats() {
        return chats;
    }

}
