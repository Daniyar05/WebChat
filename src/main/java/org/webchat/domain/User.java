package org.webchat.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
public class User {
    @Getter
    private final String id;
    private final List<String> chats;
    @Getter
    @Setter
    private String username;
    @Setter
    @Getter
    private String mood;


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


    public void addChat(Chat chat) {
        chats.add(chat.getIdChat());
    }

    public List<String> getIdChats() {
        return chats;
    }

}
