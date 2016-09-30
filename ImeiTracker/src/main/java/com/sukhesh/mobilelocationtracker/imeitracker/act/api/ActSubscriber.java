package com.sukhesh.mobilelocationtracker.imeitracker.act.api;

import com.sukhesh.mobilelocationtracker.imeitracker.exception.ActHandlerException;

import java.util.concurrent.Callable;

/**
 * Created by sukhesh on 30/09/16.
 */
public interface ActSubscriber{
    public ActType getTypeInterestedIn();
    public void handleAct(Act act) throws ActHandlerException;
}
