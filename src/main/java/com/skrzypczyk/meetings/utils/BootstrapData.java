package com.skrzypczyk.meetings.utils;

import com.skrzypczyk.meetings.model.*;
import com.skrzypczyk.meetings.service.category.CategoryServiceImpl;
import com.skrzypczyk.meetings.service.event.EventServiceImpl;
import com.skrzypczyk.meetings.service.place.PlaceServiceImpl;
import com.skrzypczyk.meetings.service.role.RoleServiceImpl;
import com.skrzypczyk.meetings.service.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class BootstrapData implements InitializingBean {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private EventServiceImpl eventService;

    @Autowired
    private PlaceServiceImpl placeService;

    private final Logger LOG = LoggerFactory.getLogger(BootstrapData.class);

    public void initData(){

        Role role = new Role("ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User administrator = new User( "Administrator", "Admin1","mateusz.skrzypczyk1@hotmail.com","Admin1", roles);
        roleService.save(role);
        userService.save(administrator);

        Category category = new Category();
        category.setName("Testowa kategoria");
        categoryService.save(category);

        Place place = new Place();
        place.setName("Politechnika Łódzka");
        place.setX(51.754226);
        place.setY(19.451949);
        placeService.save(place);

        Event event = new Event();
        event.setCategory(category);
        event.setDescription("Testowe wydarzenie");
        event.setPlaceOfMeeting(place);
        event.setSeats(500);
        event.setTitle("TEST");
        eventService.save(event);


    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.info("Bootstrapping data...");
        initData();
        LOG.info("...Bootstrapping completed");
    }
}
