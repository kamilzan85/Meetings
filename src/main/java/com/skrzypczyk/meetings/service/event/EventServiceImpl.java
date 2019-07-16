package com.skrzypczyk.meetings.service.event;

import com.skrzypczyk.meetings.model.Event;
import com.skrzypczyk.meetings.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void save(Event event) {
        eventRepository.save(event);
    }
}
