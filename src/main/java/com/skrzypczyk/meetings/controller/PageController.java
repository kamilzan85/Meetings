package com.skrzypczyk.meetings.controller;

import com.skrzypczyk.meetings.model.Event;
import com.skrzypczyk.meetings.model.User;
import com.skrzypczyk.meetings.service.email.EmailService;
import com.skrzypczyk.meetings.service.event.EventService;
import com.skrzypczyk.meetings.service.user.UserService;
import com.skrzypczyk.meetings.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PageController {

    private final EventService eventService;

    @Autowired
    public PageController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = {"/home", "/"})
    public String homePage(Model model){
        List<Event> eventList = eventService.findNewestPosts().getContent();
        model.addAttribute("events", eventList);
        return "index";
    }
}
