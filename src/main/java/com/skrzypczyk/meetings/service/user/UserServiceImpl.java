package com.skrzypczyk.meetings.service.user;

import com.skrzypczyk.meetings.model.User;
import com.skrzypczyk.meetings.repository.RoleRepository;
import com.skrzypczyk.meetings.repository.UserRepository;
import com.skrzypczyk.meetings.security.SecurityConfig;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SecurityConfig securityConfig;

    @Override
    public User save(User user) {
        user.setEncodedPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        user.setPassword(null);
        user.setActivationToken(RandomString.make(25));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> activatingUser(String activationToken) {
        userRepository.enableUser(activationToken);
        return userRepository.findByActivationToken(activationToken);
    }
}
