package com.skrzypczyk.meetings.repository;

import com.skrzypczyk.meetings.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EventRepository extends JpaRepository<Event, Long> {
    Event findByIdentity(String identity);
}
