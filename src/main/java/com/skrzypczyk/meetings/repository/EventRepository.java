package com.skrzypczyk.meetings.repository;

import com.skrzypczyk.meetings.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
