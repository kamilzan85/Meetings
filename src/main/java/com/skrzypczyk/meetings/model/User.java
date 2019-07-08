package com.skrzypczyk.meetings.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Transient
    private String password;

    private String encodedPassword;

    @Column(unique = true)
    private String email;

    @Transient
    private String passwordConfirm;

    @ManyToMany
    private Set<Role> roles;

    public User() {
    }

    public User(String username, String password, String email, String passwordConfirm, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.passwordConfirm = passwordConfirm;
        this.roles = roles;
    }
}
