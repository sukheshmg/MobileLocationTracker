package com.sukhesh.mobilelocationtracker.imeitracker.act.impl;

import com.sukhesh.mobilelocationtracker.imeitracker.act.api.Act;
import com.sukhesh.mobilelocationtracker.imeitracker.act.api.ActSubscriber;
import com.sukhesh.mobilelocationtracker.imeitracker.act.api.ActType;
import com.sukhesh.mobilelocationtracker.imeitracker.exception.ActHandlerException;

/**
 * Created by sukhesh on 30/09/16.
 */
public class MessageReadyAct implements ActSubscriber {
    @Override
    public ActType getTypeInterestedIn() {
        return ActType.MESSAGE_READY_TO_SEND;
    }

    @Override
    public void handleAct(Act act) throws ActHandlerException {

    }
}
