package com.sukhesh.mobilelocationtracker.imeitracker.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sukhesh on 02/10/16.
 */
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @Column(name = "messageId", nullable = false, unique = true)
    private String messageId;

    @Basic
    @Column(name = "deviceId", nullable = false, unique = false)
    private String deviceId;

    @Basic
    @Column(name = "message", nullable = false, unique = false)
    private String message;

    @Basic
    @Column(name = "eventName", nullable = false, unique = false)
    private String eventName;

    @Basic
    @Column(name = "response", nullable = true, unique = false)
    private String response;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="createdTime",nullable = false)
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updatedTime",nullable = true)
    private Date updatedTime;

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

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
