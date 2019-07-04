package com.skrzypczyk.meetings.repository;

import com.skrzypczyk.meetings.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
