package com.app.controller;

import com.app.dto.AuthenticationDto;
import com.app.dto.CreateUserDto;
import com.app.model.User;
import com.app.service.UserService;
import com.app.service.proxy.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        if (authenticationService.isAdmin()){
            return "denied";
        }
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("TEST", "TEST");
        return "user";
    }


    @GetMapping("/all")
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("/delete/{id}")
    public Integer deleteById(@PathVariable Integer id) {
        return (userService.delete(id)) ? 1 : 0;
    }

}
