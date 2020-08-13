package com.app.controller;

import com.app.model.User;
import com.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

/*    @GetMapping("/show/{id}")
    public User findById(@PathVariable Integer id) {
        return userService.findById(id);
    }*/

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
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
