package com.sukhesh.mobilelocationtracker.imeitracker.db;

import com.sukhesh.mobilelocationtracker.imeitracker.model.ImeiLocation;
import com.sukhesh.mobilelocationtracker.imeitracker.model.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sukhesh on 02/10/16.
 */
@Repository
@Transactional
@Component
public interface MessageRepo extends JpaRepository<Message, String> {

    @Query("select message from Message message where message.messageId=:messageId and message.deviceId=:deviceId and message.eventName = :event")
    public List<Message> getMessageWithMessageIdAndDeviceId(@Param("messageId") String messageId, @Param("deviceId") String deviceId, @Param("event") String event, Pageable pageable);

    @Query("select message from Message message where message.deviceId=:deviceId and message.eventName = :event")
    public List<Message> getMessageWithEventAndDeviceId( @Param("deviceId") String deviceId, @Param("event") String event, Pageable pageable);
}
