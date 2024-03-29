package com.app.controller;

import com.app.dto.CreateUserDto;
import com.app.model.User;
import com.app.service.UserService;
import com.app.validator.CreateUserDtoValidator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class AuthController {

    private UserService userService;
    private CreateUserDtoValidator validator;

    @GetMapping("/sign-up")
    public String register(Model model){
        model.addAttribute("userDto",new CreateUserDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(CreateUserDto createUserDto){
        userService.createUser(createUserDto);
        return "register";
    }
}
