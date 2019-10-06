package com.skrzypczyk.meetings.controller;

import com.skrzypczyk.meetings.model.Event;
import com.skrzypczyk.meetings.model.Place;
import com.skrzypczyk.meetings.service.category.CategoryService;
import com.skrzypczyk.meetings.service.event.EventService;
import com.skrzypczyk.meetings.service.place.PlaceService;
import com.skrzypczyk.meetings.service.user.UserService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class EventController {

    private final EventService eventService;
    private final UserService userService;
    private final EventValidator eventValidator;
    private final CategoryService categoryService;

    public EventController(EventService eventService, UserService userService, EventValidator eventValidator, CategoryService categoryService) {
        this.eventService = eventService;
        this.userService = userService;
        this.eventValidator = eventValidator;
        this.categoryService = categoryService;
    }

    @GetMapping("/events/{identity}")
    public String singleEventView(@PathVariable String identity, Model model) {
        Event event = eventService.findEventByIdentity(identity);
        model.addAttribute("event", event);
        model.addAttribute("mapApiKey", ExtendedProperties.INSTANCE.getGoogleApiKey());
        return "event";
    }

    @GetMapping("/new-event")
    public String newEvent(Model model) {
        model.addAttribute("mapApiKey", ExtendedProperties.INSTANCE.getGoogleApiKey());
        model.addAttribute("categories", categoryService.findAll());
        if(model.asMap().get("eventForm")==null){
            model.addAttribute("eventForm", new Event());
        }
        return "add-event";
    }

    @PostMapping("/new-event")
    public String createNewPost(@Valid @ModelAttribute("eventForm") final Event eventForm,
                                final BindingResult bindingResult,
                                final RedirectAttributes redirectAttributes,
                                Principal principal) {
        eventValidator.validate(eventForm, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.eventForm", bindingResult);
            redirectAttributes.addFlashAttribute("eventForm", eventForm);
            return "redirect:/new-event";
        }
        eventForm.setOrganizer(userService.findByUsername(principal.getName()));
        eventService.save(eventForm);
        return "redirect:/events/" + eventForm.getIdentity();
    }
}
