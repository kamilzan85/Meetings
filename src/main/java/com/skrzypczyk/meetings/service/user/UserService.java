package com.skrzypczyk.meetings.service.user;

import com.skrzypczyk.meetings.model.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
