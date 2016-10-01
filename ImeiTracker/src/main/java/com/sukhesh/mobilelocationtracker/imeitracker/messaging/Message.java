package com.sukhesh.mobilelocationtracker.imeitracker.messaging;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sukhesh on 30/09/16.
 */
public class Message {
    private Map<String, String> data = new HashMap<>();
    private String to;

    public Message(String id, String message, String to) {
        this.to = to;
        setNotification(message);
        setMessageId(id);
    }

    private void setTo(String to) {
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    private void setNotification(String message) {
        data.put("message", message);
    }

    public String getNotification() {
        return data.get("message");
    }

    private void setMessageId(String id) {
        data.put("id", id);
    }

    public long getMessageId() {
        return Long.parseLong(data.get("id"));
    }
}
