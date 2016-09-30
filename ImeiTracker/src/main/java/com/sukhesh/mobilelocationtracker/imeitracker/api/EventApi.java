package com.sukhesh.mobilelocationtracker.imeitracker.api;

import com.sukhesh.mobilelocationtracker.imeitracker.db.EventPersister;
import com.sukhesh.mobilelocationtracker.imeitracker.db.EventRepo;
import com.sukhesh.mobilelocationtracker.imeitracker.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * Created by sukhesh on 30/09/16.
 */

@RestController
@RequestMapping(value = "v1/eventmanager/")
@Component
public class EventApi {
    private static final Logger logger = LoggerFactory.getLogger(EventApi.class);

    @Autowired
    EventPersister persister;

    @Autowired
    EventRepo repo;

    @RequestMapping(value = "event/", method = RequestMethod.POST)
    public void createEvent(@RequestParam(name = "langitude", required = true) Double langitude,
                            @RequestParam(name = "latitude", required = true) Double latitude,
                            @RequestParam(name = "range", required = true) int range,
                            @RequestParam(name = "name", required = true) String name,
                            @RequestParam(name = "message", required = true) String message) throws ValidationException {
        logger.info("Creating event " + name + " at (" + langitude + ", " + latitude + ") with range " + range + " meters");

        Event event = new Event();
        event.setLangitude(langitude);
        event.setLatitude(latitude);
        event.setRange(range);
        event.setName(name);
        event.setMessage(message);

        List<Event> events = repo.getEventWithName(name, new PageRequest(0, 1));
        if(events != null && events.size() != 0) {
            throw new ValidationException("An event with name " + name + " already exists.");
        }
        persister.save(event);

    }
}
