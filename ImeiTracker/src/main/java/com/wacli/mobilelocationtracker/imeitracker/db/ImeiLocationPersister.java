package com.wacli.mobilelocationtracker.imeitracker.db;

import com.wacli.mobilelocationtracker.imeitracker.model.ImeiLocation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by sukhesh on 16/04/16.
 */

@Repository
@Transactional
@Component
public interface ImeiLocationPersister extends CrudRepository<ImeiLocation, String> {
    @Modifying
    @Query("update ImeiLocation location set location.langitude= :langitude, location.latitude = :latitude, location.seenTime = :time where location.imei = :imei")
    public void setLastSeenLocation(@Param("imei") String imei, @Param("langitude")double langitude, @Param("latitude")double latitude, @Param("time") Date time);
}
