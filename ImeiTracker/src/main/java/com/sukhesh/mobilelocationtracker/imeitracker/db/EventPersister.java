package com.sukhesh.mobilelocationtracker.imeitracker.db;

import com.sukhesh.mobilelocationtracker.imeitracker.model.Event;
import com.sukhesh.mobilelocationtracker.imeitracker.model.ImeiLocation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sukhesh on 30/09/16.
 */

@Repository
@Transactional
@Component
public interface EventPersister extends CrudRepository<Event, Long> {
}
