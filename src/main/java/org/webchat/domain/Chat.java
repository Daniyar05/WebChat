package org.webchat.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Chat {
    List<Message> history;
    final String idChat;


    public Chat() {
        idChat = UUID.randomUUID().toString();
        history = new ArrayList<>();
    }

    public List<Message> getHistory() {
        return history;
    }

    public String getIdChat() {
        return idChat;
    }

    public void addMessage(Message message){
        history.add(message);
    }
}
