package org.webchat.domain;

import java.util.Date;
import java.util.Objects;

public final class Message {
    private final Date data;
    private final String idFrom;
    private final String content;

    public Message(String idFrom, String content) {
        this.idFrom = idFrom;
        this.content = content;
        data = new Date();

    }

    public String idFrom() {
        return idFrom;
    }


    public String content() {
        return content;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj == this) return true;
//        if (obj == null || obj.getClass() != this.getClass()) return false;
//        var that = (Message) obj;
//        return Objects.equals(this.idFrom, that.idFrom) &&
//                Objects.equals(this.idChat, that.idChat) &&
//                Objects.equals(this.content, that.content);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(idFrom, idChat, content);
//    }

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