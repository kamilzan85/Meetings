package com.skrzypczyk.meetings.service.user;

import com.skrzypczyk.meetings.model.User;
import java.util.Optional;

public interface UserService {
    User save(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    Optional<User> activatingUser(String token);
}
