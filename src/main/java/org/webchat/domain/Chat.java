package org.webchat.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Chat {
    final String idChat;
    List<Message> history;


    public Chat() {
        idChat = UUID.randomUUID().toString();
        history = new ArrayList<>();
    }

    public Chat(String idChat, List<Message> history) {
        this.idChat = idChat;
        this.history = history;
    }

    public List<Message> getHistory() {
        return history;
    }

    public String getIdChat() {
        return idChat;
    }

    public void addMessage(Message message) {
        history.add(message);
    }
}
