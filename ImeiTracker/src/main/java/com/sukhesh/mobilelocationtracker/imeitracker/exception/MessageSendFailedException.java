package com.sukhesh.mobilelocationtracker.imeitracker.exception;

/**
 * Created by sukhesh on 30/09/16.
 */
public class MessageSendFailedException extends Exception {

    private String message;

    public MessageSendFailedException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
