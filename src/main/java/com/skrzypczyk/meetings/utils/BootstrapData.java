package com.skrzypczyk.meetings.utils;

import com.skrzypczyk.meetings.model.Role;
import com.skrzypczyk.meetings.model.User;
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

    private final Logger LOG = LoggerFactory.getLogger(BootstrapData.class);

    public void initData(){

        Role role = new Role("ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        User administrator = new User( "Administrator", "Admin1","mateusz.skrzypczyk1@hotmail.com","Admin1", roles);
        roleService.save(role);
        userService.save(administrator);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        LOG.info("Bootstrapping data...");
        initData();
        LOG.info("...Bootstrapping completed");
    }
}
