package com.skrzypczyk.meetings.service.user;

import com.skrzypczyk.meetings.model.User;
import com.skrzypczyk.meetings.repository.RoleRepository;
import com.skrzypczyk.meetings.repository.UserRepository;
import com.skrzypczyk.meetings.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SecurityConfig securityConfig;

    @Override
    public void save(User user) {
        user.setEncodedPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        user.setPassword(null);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
