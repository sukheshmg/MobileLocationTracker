package com.sukhesh.mobilelocationtracker.imeitracker.db;

import com.sukhesh.mobilelocationtracker.imeitracker.model.Event;
import com.sukhesh.mobilelocationtracker.imeitracker.model.ImeiLocation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sukhesh on 30/09/16.
 */
@Repository
// @Transactional
@Component
public interface EventRepo extends JpaRepository<Event, Long> {
    @Query("select event from Event event where event.name=:name")
    public List<Event> getEventWithName(@Param("name") String name, Pageable pageable);
}
