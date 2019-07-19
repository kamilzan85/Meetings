package com.skrzypczyk.meetings.controller;

import com.skrzypczyk.meetings.model.Event;
import com.skrzypczyk.meetings.model.User;
import com.skrzypczyk.meetings.service.event.EventService;
import com.skrzypczyk.meetings.service.user.UserService;
import com.skrzypczyk.meetings.utils.ExtendedProperties;
import com.skrzypczyk.meetings.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping(value = {"/home", "/"})
    public String homePage(Model model){
        List<Event> eventList = eventService.findNewestPosts().getContent();
        model.addAttribute("events", eventList);
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value="error", required = false)String error, @RequestParam(value="logout", required = false)String logout, Model model){
        String errorMsg = "";
        if(error != null){
            errorMsg = "Username or Password is incorrect!";
        }
        if(logout!=null){
            errorMsg="You have been successfully logged out!";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth instanceof AnonymousAuthenticationToken)){
            return "redirect:/home";
        }
        model.addAttribute("msg", errorMsg);
        return "login";
    }

    @GetMapping(value="/logout")
    public void logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("userForm") User userForm, BindingResult bindingResult){
        userValidator.validate(userForm, bindingResult);
        if(bindingResult.hasErrors()){
            return "registration";
        }
        userService.save(userForm);
        return "redirect:/home";
    }
}
