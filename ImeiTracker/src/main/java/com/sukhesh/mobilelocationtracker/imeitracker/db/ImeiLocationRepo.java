package com.sukhesh.mobilelocationtracker.imeitracker.db;

import com.sukhesh.mobilelocationtracker.imeitracker.model.ImeiLocation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by sukhesh on 16/04/16.
 */
@Repository
// @Transactional
@Component
public interface ImeiLocationRepo extends JpaRepository<ImeiLocation, String>{
    @Query("select location from ImeiLocation location where location.imei=:imei order by location.seenTime desc")
    public List<ImeiLocation> getLocationsOrderedByTime(@Param("imei") String imei, Pageable pageable);

    @Query("select location from ImeiLocation location where location.seenTime>=:startTime")
    public List<ImeiLocation> getLocationsChangedAfter(@Param("startTime") Date startTime, Pageable pageable);

}
