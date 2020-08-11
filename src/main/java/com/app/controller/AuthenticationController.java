package com.app.controller;

import com.app.dto.AuthenticationDto;
import com.app.dto.CreateUserDto;
import com.app.model.User;
import com.app.service.UserService;
import com.app.service.proxy.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @GetMapping("/test")
    public User test(){
        return authenticationService.getUser();
    }

    @GetMapping("/logout")
    public void logout() {
        authenticationService.logout();
    }

    @GetMapping("/login")
    public void login() {
        AuthenticationDto dto = new AuthenticationDto();
        dto.setUsername("adam");
        dto.setPassword(DigestUtils.sha256Hex("123"));
        System.out.println(authenticationService.login(dto));
    }

    @GetMapping("/register")
    public void register() {
        if (authenticationService.isLoggedIn()) {
            throw new IllegalStateException("You are logged in. Can't register");
        }
        var userToAdd = CreateUserDto
                .builder()
                .name("Adam")
                .surname("Nowak")
                .email("janekw93@o2.pl")
                .password(DigestUtils.sha256Hex("123"))
                .repeatedPassword(DigestUtils.sha256Hex("123"))
                .username("adam")
                .build();
        userService.createUser(userToAdd);
        System.out.println("User " + userToAdd.getUsername() + " register successfully.");
        // EmailServiceKm.send(userToAdd.getEmail(), "Hello!", "<h1>Welcome in our site!</h1>");
    }
}
