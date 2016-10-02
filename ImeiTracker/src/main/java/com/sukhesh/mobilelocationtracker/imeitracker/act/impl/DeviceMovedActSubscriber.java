package com.sukhesh.mobilelocationtracker.imeitracker.act.impl;

import com.sukhesh.mobilelocationtracker.imeitracker.act.api.Act;
import com.sukhesh.mobilelocationtracker.imeitracker.act.api.ActSubscriber;
import com.sukhesh.mobilelocationtracker.imeitracker.act.api.ActType;
import com.sukhesh.mobilelocationtracker.imeitracker.db.EventRepo;
import com.sukhesh.mobilelocationtracker.imeitracker.db.ImeiLocationRepo;
import com.sukhesh.mobilelocationtracker.imeitracker.db.MessageRepo;
import com.sukhesh.mobilelocationtracker.imeitracker.exception.ActHandlerException;
import com.sukhesh.mobilelocationtracker.imeitracker.model.Event;
import com.sukhesh.mobilelocationtracker.imeitracker.model.Message;
import com.sukhesh.mobilelocationtracker.imeitracker.util.BeanLookupHelper;
import com.sukhesh.mobilelocationtracker.imeitracker.util.GeoLocationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

/**
 * Created by sukhesh on 30/09/16.
 */
public class DeviceMovedActSubscriber implements ActSubscriber {

    @Autowired
    EventRepo eventRepo;
    

    @Override
    public ActType getTypeInterestedIn() {
        return ActType.DEVICE_MOVED;
    }

    @Override
    public void handleAct(Act _act) throws ActHandlerException {
        DeviceMovedAct act = (DeviceMovedAct) _act;
        eventRepo = BeanLookupHelper.INSTANCE.getEventRepo();
        List<Event> events = eventRepo.findAll();
        for(Event event:events) {
            if(GeoLocationHelper.isWithinRange(event, act.getLocation(), event.getRange())) {
                List<Message> existingMessages = BeanLookupHelper.INSTANCE.getMessageRepo().getMessageWithEventAndDeviceId(act.getLocation().getImei(), event.getName(), new PageRequest(0, 1));

                String messageId = UUID.randomUUID().toString();
                if(existingMessages != null && existingMessages.size() != 0) {
                    messageId = existingMessages.get(0).getMessageId();
                }

                System.out.println("Sending message " + event.getMessage() + " to " + act.getLocation().getImei());
                MessageReadyToSendAct messageReadyToSendAct = new MessageReadyToSendAct();
                messageReadyToSendAct.setMessage(event.getMessage());
                messageReadyToSendAct.setDeviceId(act.getLocation().getImei());
                messageReadyToSendAct.setMessageId(messageId);
                messageReadyToSendAct.setEventName(event.getName());

                BeanLookupHelper.INSTANCE.getActManager().sendAct(messageReadyToSendAct);
            }
        }
    }
}
