package com.sukhesh.mobilelocationtracker.imeitracker.act.impl;

import com.sukhesh.mobilelocationtracker.imeitracker.act.api.Act;
import com.sukhesh.mobilelocationtracker.imeitracker.act.api.ActType;

/**
 * Created by sukhesh on 30/09/16.
 */
public class MessageReadyToSendAct implements Act {
    @Override
    public ActType getActType() {
        return ActType.MESSAGE_READY_TO_SEND;
    }

    private String messageId;
    private String deviceId;
    private String message;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
