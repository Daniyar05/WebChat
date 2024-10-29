package org.webchat.domain;

import java.io.Serializable;
import java.util.Date;

public final class Message implements Serializable {
    private final Date data;
    private final String content;
    private final User userFrom;

    public Message(User userFrom, String content) {
        this.userFrom = userFrom;
        this.content = content;
        data = new Date();
    }

    public Message(Date data, User userFrom, String content) {
        this.data = data;
        this.userFrom = userFrom;
        this.content = content;
    }

    public User userFrom() {
        return userFrom;
    }


    public String content() {
        return content;
    }

    @Override
    public String toString() {
        return "Message[" +
                "userFrom=" + userFrom + ", " +
                "content=" + content + ']';
    }

    public Date getData() {
        return data;
    }
}