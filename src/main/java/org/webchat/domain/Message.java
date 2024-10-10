package org.webchat.domain;

import java.io.Serializable;
import java.util.Date;

public final class Message implements Serializable {
    private final Date data;
    private final String idFrom;
    private final String content;

    public Message(String idFrom, String content) {
        this.idFrom = idFrom;
        this.content = content;
        data = new Date();
    }

    public Message(Date data, String idFrom, String content) {
        this.data = data;
        this.idFrom = idFrom;
        this.content = content;
    }

    public String idFrom() {
        return idFrom;
    }


    public String content() {
        return content;
    }

    @Override
    public String toString() {
        return "Message[" +
                "idFrom=" + idFrom + ", " +
                "content=" + content + ']';
    }

    public Date getData() {
        return data;
    }
}