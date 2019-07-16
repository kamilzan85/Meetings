package com.skrzypczyk.meetings.controller;
import com.skrzypczyk.meetings.model.Event;
import com.skrzypczyk.meetings.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events/{identity}")
    public String singlePost(@PathVariable String identity, Model model){
        Event event = eventService.findEventByIdentity(identity);
        model.addAttribute("event", event);
        return "event";
    }
}
