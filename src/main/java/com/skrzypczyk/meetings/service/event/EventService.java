package com.skrzypczyk.meetings.service.event;

import com.skrzypczyk.meetings.model.Event;
import org.springframework.data.domain.Page;

public interface EventService {
    void save(Event event);
    Page<Event> findNewestPosts();
    Event findEventByIdentity(String identity);
}
