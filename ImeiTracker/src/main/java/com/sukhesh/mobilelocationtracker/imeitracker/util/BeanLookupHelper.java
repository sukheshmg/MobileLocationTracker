package com.sukhesh.mobilelocationtracker.imeitracker.util;

import com.sukhesh.mobilelocationtracker.imeitracker.act.api.ActManager;
import com.sukhesh.mobilelocationtracker.imeitracker.db.*;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by sukhesh on 16/04/16.
 */
public enum BeanLookupHelper {
    INSTANCE;

    private ConfigurableApplicationContext context;

    public void init(ConfigurableApplicationContext context) {
        this.context = context;
    }

    public ConfigurableApplicationContext getContext() {
        return context;
    }

    public void setContext(ConfigurableApplicationContext context) {
        this.context = context;
    }

    public EventRepo getEventRepo() {
        return (EventRepo) context.getBean("eventRepo");
    }

    public EventPersister getEventPersister() {
        return (EventPersister) context.getBean("eventPersister");
    }

    public ImeiLocationRepo getImeiLocationRepo() {
        return (ImeiLocationRepo) context.getBean("imeiLocationRepo");
    }

    public ImeiLocationPersister getImeiLocationPersister() {
        return (ImeiLocationPersister) context.getBean("imeiLocationPersister");
    }

    public ActManager getActManager() {
        return (ActManager) context.getBean("actManagerImpl");
    }

    public MessagePersister getMessagePersister() {
        return (MessagePersister) context.getBean("messagePersister");
    }

    public MessageRepo getMessageRepo() {
        return (MessageRepo) context.getBean("messageRepo");
    }
}
