package com.sukhesh.mobilelocationtracker.imeitracker.act.impl;

import com.sukhesh.mobilelocationtracker.imeitracker.act.api.Act;
import com.sukhesh.mobilelocationtracker.imeitracker.act.api.ActSubscriber;
import com.sukhesh.mobilelocationtracker.imeitracker.act.api.ActType;
import com.sukhesh.mobilelocationtracker.imeitracker.db.MessagePersister;
import com.sukhesh.mobilelocationtracker.imeitracker.exception.ActHandlerException;
import com.sukhesh.mobilelocationtracker.imeitracker.exception.MessageSendFailedException;
import com.sukhesh.mobilelocationtracker.imeitracker.messaging.FirebaseMessagingUtil;
import com.sukhesh.mobilelocationtracker.imeitracker.messaging.Message;
import com.sukhesh.mobilelocationtracker.imeitracker.util.BeanLookupHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by sukhesh on 30/09/16.
 */
public class MessageReadyActSubscriber implements ActSubscriber {
    @Override
    public ActType getTypeInterestedIn() {
        return ActType.MESSAGE_READY_TO_SEND;
    }


    @Override
    public void handleAct(Act _act) throws ActHandlerException {
        MessageReadyToSendAct act = (MessageReadyToSendAct) _act;

        Message message = new Message(act.getMessageId(), act.getMessage(), act.getEventName(), act.getDeviceId());

        com.sukhesh.mobilelocationtracker.imeitracker.model.Message dbMessage = new com.sukhesh.mobilelocationtracker.imeitracker.model.Message();
        dbMessage.setDeviceId(act.getDeviceId());
        dbMessage.setMessageId(act.getMessageId());
        dbMessage.setMessage(act.getMessage());
        dbMessage.setEventName(act.getEventName());
        dbMessage.setCreatedTime(new Date());

        BeanLookupHelper.INSTANCE.getMessagePersister().save(dbMessage);

        try {
            FirebaseMessagingUtil.sendMessage(message);
        } catch (MessageSendFailedException e) {
            e.printStackTrace();
        }
    }
}
