package org.webchat.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.*;


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
//        Collections.reverse(history);
        this.history = history;
    }

    public void addMessage(Message message) {
        history.add(message);
    }

    public Optional<List<Message>> getHistory(int offset, int limit) {
        int size = history.size();
        if (offset > size){return Optional.of(new ArrayList<>());}
        List<Message> tempList = new ArrayList<>();
        int start = Math.max(size - limit*(offset/limit+1), 0);
        for (int i = start; i < size && i < start+limit; i++) {
            tempList.add(history.get(i));
        }
        return Optional.of(tempList);
//        return Optional.of(this.getHistory().stream()
//                        .skip(offset)
//                        .limit(limit)
//                        .collect(Collectors.toList()));
    }
}
