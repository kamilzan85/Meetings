package com.skrzypczyk.meetings.controller;

import com.skrzypczyk.meetings.model.Category;
import com.skrzypczyk.meetings.model.Event;
import com.skrzypczyk.meetings.security.SecurityUtils;
import com.skrzypczyk.meetings.service.category.CategoryService;
import com.skrzypczyk.meetings.service.event.EventService;
import com.skrzypczyk.meetings.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class PageController {

    private final EventService eventService;

    private final CategoryService categoryService;

    private final UserService userService;

    @Autowired
    public PageController(EventService eventService, CategoryService categoryService, UserService userService) {
        this.eventService = eventService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping(value = {"/home", "/"})
    public String homePage(Model model){
        List<Event> eventList = eventService.findNewestPosts().getContent();
        List<Category> categoryList = categoryService.findPageOfCategories().getContent();
        model.addAttribute("events", eventList);
        model.addAttribute("categories", categoryList);
        return "index";
    }

    @GetMapping(value="/events")
    public String allEvents(Model model, HttpServletRequest request){
        String username = SecurityUtils.getLoggedUserNameFromRequest(request);
        model.addAttribute("subscribed", userService.findByUsername(username).getEvents());
        model.addAttribute("events", eventService.findAllEvents());
        return "all-events";
    }
}
