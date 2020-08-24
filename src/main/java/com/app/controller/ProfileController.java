package com.app.controller;

import com.app.dto.EditProfileDto;
import com.app.mappers.Mapper;
import com.app.model.User;
import com.app.model.thymeleaf.SeanceWithObj;
import com.app.service.MovieService;
import com.app.service.SeanceService;
import com.app.service.TicketService;
import com.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final TicketService ticketService;

    @GetMapping("/tickets")
    public String profileTickets(Model model, Authentication authentication){
        User currentUser = userService.findByUsername(authentication.getName());
        model.addAttribute("tickets",ticketService.findByUserId(currentUser.getId()));
        return "user_tickets";
    }

    @GetMapping("/edit")
    public String editProfile(Model model, Authentication authentication){
        EditProfileDto editProfileDto = Mapper.fromUserToEditProfileDto(userService.findByUsername(authentication.getName()));
        model.addAttribute("user",editProfileDto);
        return "profile";
    }

    @PostMapping("/edit")
    public String editedProfile (@ModelAttribute EditProfileDto editProfileDto){
        if (editProfileDto.getNewPassword().isEmpty()){
            editProfileDto.setNewPassword(editProfileDto.getPassword());
        }
        User user = Mapper.fromEditProfileDtoToUser(editProfileDto);
        userService.edit(user);
        return "index";
    }
}
