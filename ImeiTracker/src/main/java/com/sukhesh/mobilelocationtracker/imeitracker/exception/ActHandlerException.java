package com.sukhesh.mobilelocationtracker.imeitracker.exception;

/**
 * Created by sukhesh on 30/09/16.
 */
public class ActHandlerException extends Exception {
    private String message;

    public ActHandlerException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
