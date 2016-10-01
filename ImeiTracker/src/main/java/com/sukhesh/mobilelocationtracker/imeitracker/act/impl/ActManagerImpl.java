package com.sukhesh.mobilelocationtracker.imeitracker.act.impl;

import com.sukhesh.mobilelocationtracker.imeitracker.act.api.Act;
import com.sukhesh.mobilelocationtracker.imeitracker.act.api.ActManager;
import com.sukhesh.mobilelocationtracker.imeitracker.act.api.ActSubscriber;
import com.sukhesh.mobilelocationtracker.imeitracker.act.api.ActType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by sukhesh on 30/09/16.
 */
@Component
public class ActManagerImpl implements ActManager{

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    List<ActSubscriber> subscribers = new CopyOnWriteArrayList<>();

    public ActManagerImpl() {
        subscribers.add(new DeviceMovedActSubscriber());
        subscribers.add(new MessageReadyActSubscriber());
    }

    @Override
    public void sendAct(Act act) {
        for(ActSubscriber subscriber:subscribers) {
            if(subscriber.getTypeInterestedIn().equals(act.getActType())) {
                try {
                    new ActThread(act, subscriber).call();
                } catch (Exception e) {
                    System.out.println("Exception in act handler " + e.getMessage());
                }
            }
        }
    }

    @Override
    public void subscribe(ActSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(ActSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    class ActThread implements Callable<Void>{
        Act act;
        ActSubscriber subscriber;

        public ActThread(Act act, ActSubscriber subscriber) {
            this.act = act;
            this.subscriber = subscriber;
        }

        @Override
        public Void call() throws Exception {
            subscriber.handleAct(act);
            return null;
        }
    }
}
