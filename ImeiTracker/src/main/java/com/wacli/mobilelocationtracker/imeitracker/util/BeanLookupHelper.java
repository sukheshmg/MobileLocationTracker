package com.wacli.mobilelocationtracker.imeitracker.util;

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
}
