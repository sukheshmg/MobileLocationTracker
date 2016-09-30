package com.sukhesh.mobilelocationtracker.imeitracker.act.impl;

import com.sukhesh.mobilelocationtracker.imeitracker.act.api.Act;
import com.sukhesh.mobilelocationtracker.imeitracker.act.api.ActType;
import com.sukhesh.mobilelocationtracker.imeitracker.model.ImeiLocation;

/**
 * Created by sukhesh on 30/09/16.
 */
public class DeviceMovedAct implements Act {
    private ImeiLocation location;

    public DeviceMovedAct(ImeiLocation location) {
        this.location = location;
    }

    public ImeiLocation getLocation() {
        return location;
    }

    public void setLocation(ImeiLocation location) {
        this.location = location;
    }

    @Override
    public ActType getActType() {
        return ActType.DEVICE_MOVED;
    }
}
