package com.wacli.mobilelocationtracker.imeitracker.conf;

import java.lang.Integer;
import java.lang.NumberFormatException;

/**
 * Created by sukhesh on 16/04/16.
 */
public enum ConfigLoader {
    INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);

    public void loadConfig() {
        String _port = System.getProperty("service.port");
        try {
            GlobalConfig.INSTANCE.setServicePort(Integer.parseInt(_port));
        } catch (NumberFormatException e) {
            logger.error("Could not parse " + _port);
        }
    }
}
