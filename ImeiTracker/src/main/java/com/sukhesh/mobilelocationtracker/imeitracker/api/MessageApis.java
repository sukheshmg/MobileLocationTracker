package com.sukhesh.mobilelocationtracker.imeitracker.api;

import com.sukhesh.mobilelocationtracker.imeitracker.db.MessagePersister;
import com.sukhesh.mobilelocationtracker.imeitracker.db.MessageRepo;
import com.sukhesh.mobilelocationtracker.imeitracker.model.Message;
import com.sukhesh.mobilelocationtracker.imeitracker.util.BeanLookupHelper;
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
 * Created by sukhesh on 02/10/16.
 */
@RestController
@RequestMapping(value = "v1/response/")
@Component
public class MessageApis {
    private static final Logger logger = LoggerFactory.getLogger(MessageApis.class);

    @Autowired
    MessageRepo messageRepo;

    @Autowired
    MessagePersister messagePersister;

    @RequestMapping(value = "message/{messageId}", method = RequestMethod.POST)
    public void setMessageResponse(@PathVariable("messageId") String messageId,
                                   @RequestParam(name = "deviceId") String deviceId,
                                   @RequestParam(name = "response") String response,
                                   @RequestParam(name = "event") String event) throws ValidationException {
        logger.info("updateing message " + messageId + " for device " + deviceId + " with response " + response);
        List<Message> messages = messageRepo.getMessageWithMessageIdAndDeviceId(messageId, deviceId, event, new PageRequest(0, 1));

        if(messages == null || messages.size() == 0) {
            throw new ValidationException("No such message with id " + messageId);
        }

        messagePersister.updateMessage(messageId,deviceId, event, response, new Date());
        // BeanLookupHelper.INSTANCE.getMessagePersister().updateMessage(messageId, deviceId, response, new Date());
    }
}
