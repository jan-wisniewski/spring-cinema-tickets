package com.app.controller;

import com.app.model.User;
import com.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    @RequestMapping("/edit")
    public String editProfile(Model model, Authentication authentication){
        model.addAttribute("user",userService.findByUsername(authentication.getName()));
        return "profile";
    }

    @PostMapping("/edit")
    public String editedProfile (@ModelAttribute User user, Model model){
        System.out.println(user);
        userService.edit(user);
        model.addAttribute("user",user);
        return "profile";
    }
}
