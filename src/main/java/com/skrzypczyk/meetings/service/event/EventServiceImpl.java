package com.skrzypczyk.meetings.service.event;

import com.skrzypczyk.meetings.model.Event;
import com.skrzypczyk.meetings.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void save(Event event) {
        eventRepository.save(event);
    }

    @Override
    public Page<Event> findNewestPosts() {
        Pageable limit = PageRequest.of(0,10);
        return eventRepository.findAll(limit);
    }

    @Override
    public Event findEventByIdentity(String identity) {
        return eventRepository.findByIdentity(identity);
    }
}
