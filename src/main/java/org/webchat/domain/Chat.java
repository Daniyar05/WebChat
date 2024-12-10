package org.webchat.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Getter
public class Chat {
    @Setter
    private String name="Noname";
    final String idChat;
    List<Message> history;


    public Chat() {
        idChat = UUID.randomUUID().toString();
        history = new ArrayList<>();
    }

    public Chat(String idChat, String name, List<Message> history) {
        this.name=name;
        this.idChat = idChat;
        this.history = history;
    }

    public void addMessage(Message message) {
        history.add(message);
    }

    public Optional<List<Message>> getHistory(int offset, int limit) {
        return Optional.of(this.getHistory().stream()
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList()));
    }
}
