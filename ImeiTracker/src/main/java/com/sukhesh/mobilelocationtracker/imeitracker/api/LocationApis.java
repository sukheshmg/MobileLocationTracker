package com.sukhesh.mobilelocationtracker.imeitracker.api;

import com.sukhesh.mobilelocationtracker.imeitracker.act.api.ActManager;
import com.sukhesh.mobilelocationtracker.imeitracker.act.impl.DeviceMovedAct;
import com.sukhesh.mobilelocationtracker.imeitracker.db.ImeiLocationPersister;
import com.sukhesh.mobilelocationtracker.imeitracker.model.ImeiLocation;
import com.sukhesh.mobilelocationtracker.imeitracker.db.ImeiLocationRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.Date;
import java.util.List;

/**
 * Created by sukhesh on 16/04/16.
 */

@RestController
@RequestMapping(value = "v1/locationtracker/")
@Component
public class LocationApis {
    private static final Logger logger = LoggerFactory.getLogger(LocationApis.class);

    @Autowired
    ImeiLocationPersister persister;

    @Autowired
    ImeiLocationRepo repo;

    @Autowired
    ActManager actManager;

    @RequestMapping(value = "location/{imei}", method = RequestMethod.POST)
    public void setImeiLocation(@PathVariable("imei") String imei, @RequestParam("langitude") Double langitude, @RequestParam("latitude") Double latitude) throws ValidationException {
        logger.info("Setting location to {}, {} for imei {}", new Object[]{langitude, latitude, imei});
        ImeiLocation location = new ImeiLocation();
        location.setImei(imei);
        double lang = 0.0;
        double lat = 0.0;

        lang = langitude;
        lat = latitude;

//        try {
//            lang = Double.parseDouble(langitude);
//        } catch (NumberFormatException e) {
//            throw new ValidationException("Could not read langitude");
//        }
//
//        try {
//            lat = Double.parseDouble(latitude);
//        } catch (NumberFormatException e) {
//            throw new ValidationException("Could not read latitude");
//        }

        location.setLangitude(lang);
        location.setLatitude(lat);
        location.setImei(imei);
        location.setSeenTime(new Date());

        List<ImeiLocation> locations = repo.getLocationsOrderedByTime(imei,new PageRequest(0, 1));

        if(locations != null && locations.size() != 0) {
            /* change to UTC */
            logger.info(imei + " already present. updating");
            persister.setLastSeenLocation(imei, lang, lat, new Date());
        } else {
            logger.info("new id " + imei);
            persister.save(location);
        }

        actManager.sendAct(new DeviceMovedAct(location));
    }


    @RequestMapping(value = "location/{imei}", method = RequestMethod.GET)
    public ImeiLocation getImeiLocation(@PathVariable("imei") String imei) {
        List<ImeiLocation> res = repo.getLocationsOrderedByTime(imei, new PageRequest(0, 1));
        if(res == null || res.size() == 0) {
            return null;
        }
        return repo.getLocationsOrderedByTime(imei, new PageRequest(0, 1)).get(0);
    }
}
