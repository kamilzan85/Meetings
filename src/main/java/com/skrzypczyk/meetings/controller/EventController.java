package com.skrzypczyk.meetings.controller;
import com.skrzypczyk.meetings.model.Event;
import com.skrzypczyk.meetings.model.Place;
import com.skrzypczyk.meetings.service.category.CategoryService;
import com.skrzypczyk.meetings.service.event.EventService;
import com.skrzypczyk.meetings.service.place.PlaceService;
import com.skrzypczyk.meetings.utils.ExtendedProperties;
import com.skrzypczyk.meetings.validator.EventValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private EventValidator eventValidator;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/events/{identity}")
    public String singleEventView(@PathVariable String identity, Model model){
        Event event = eventService.findEventByIdentity(identity);
        model.addAttribute("event", event);
        return "event";
    }

    @GetMapping("/new-event")
    public String newEvent(Model model){
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("eventForm", new Event());
        return "add-event";
    }

    @PostMapping("/new-event")
    public String createNewPost(@Valid @ModelAttribute("eventForm") Event eventForm, BindingResult bindingResult){
        eventValidator.validate(eventForm, bindingResult);
        if(bindingResult.hasErrors()){
            return "add-event";
        }
        Place place = new Place();
                place.setName(eventForm.getPlaceOfMeeting().getName());
                place.setX(eventForm.getPlaceOfMeeting().getX());
                place.setY(eventForm.getPlaceOfMeeting().getY());
        placeService.save(place);
        eventForm.setPlaceOfMeeting(place);
        eventService.save(eventForm);
        return "redirect:/events/"+eventForm.getIdentity();
    }
}
