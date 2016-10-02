package com.sukhesh.mobilelocationtracker.imeitracker.db;

import com.sukhesh.mobilelocationtracker.imeitracker.model.ImeiLocation;
import com.sukhesh.mobilelocationtracker.imeitracker.model.Message;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by sukhesh on 02/10/16.
 */

@Repository
@Transactional
@Component
public interface MessagePersister extends CrudRepository<Message, String> {

    @Modifying
    @Query("update Message message set message.response= :response, message.updatedTime = :updatedTime where message.messageId = :messageId and message.deviceId = :deviceId and message.eventName = :event")
    public void updateMessage(@Param("messageId") String messageId, @Param("deviceId")String deviceId, @Param("event")String event,  @Param("response")String response, @Param("updatedTime") Date updatedTime);
}
