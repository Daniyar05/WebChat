package org.webchat.domain;

import java.util.Date;

public final class Message  {
    private final Date date;
    private final String content;
    private final User userFrom;

    public Message(User userFrom, String content) {
        this.userFrom = userFrom;
        this.content = content;
        this.date = new Date();
    }

    public Message(Date date, User userFrom, String content) {
        this.date = date;
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
        return date;
    }
}