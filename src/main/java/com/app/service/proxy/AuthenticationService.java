package com.app.service.proxy;

import com.app.dto.AuthenticationDto;
import com.app.enums.Role;
import com.app.exception.AuthenticationException;
import com.app.model.User;
import com.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private User user;

    public String login(AuthenticationDto authenticationDto) {
        var user = userRepository
                .findByUsername(authenticationDto.getUsername())
                .orElseThrow(() -> new AuthenticationException("Cannot find user in db"));
        if (!Objects.equals(user.getPassword(), authenticationDto.getPassword())) {
            throw new AuthenticationException("Password is not correct");
        }

        this.user = user;
        return this.user.getUsername();
    }

    public String logout() {
        var username = this.user.getUsername();
        this.user = null;
        return username;
    }

    public boolean isUser() {
        return this.user != null && this.user.getRole().equals(Role.USER);
    }

    public boolean isAdmin() {
        return this.user != null && this.user.getRole().equals(Role.ADMIN);
    }

    public boolean isLoggedIn(){
        return this.user!=null;
    }

    public User getUser(){
        if (this.user==null){
            throw new IllegalStateException("User is null");
        }
        return this.user;
    }
}
