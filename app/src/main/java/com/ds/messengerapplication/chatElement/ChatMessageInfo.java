package com.ds.messengerapplication.chatElement;

import java.util.Date;

public class ChatMessageInfo {
    private final String message;
    private final String fromUser;
    private final long time;

    public ChatMessageInfo(String message, String fromUser) {
        this.message = message;
        this.fromUser = fromUser;

        time = new Date().getTime();
    }

    public String getMessage() {
        return message;
    }

    public String getFromUser() {
        return fromUser;
    }

    public long getTime() {
        return time;
    }
}
