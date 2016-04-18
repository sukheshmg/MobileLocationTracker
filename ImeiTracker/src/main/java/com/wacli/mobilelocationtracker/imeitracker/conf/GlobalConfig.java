package com.wacli.mobilelocationtracker.imeitracker.conf;

/**
 * Created by sukhesh on 16/04/16.
 */
public enum GlobalConfig {
    INSTANCE;

    private int servicePort = 8080;

    public int getServicePort() {
        return servicePort;
    }

    public void setServicePort(int servicePort) {
        this.servicePort = servicePort;
    }
}
