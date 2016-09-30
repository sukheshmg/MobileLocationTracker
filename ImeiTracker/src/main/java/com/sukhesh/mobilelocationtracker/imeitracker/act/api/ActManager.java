package com.sukhesh.mobilelocationtracker.imeitracker.act.api;

/**
 * Created by sukhesh on 30/09/16.
 */
public interface ActManager {
    public void sendAct(Act act);
    public void subscribe(ActSubscriber subscriber);
    public void unsubscribe(ActSubscriber subscriber);
}
