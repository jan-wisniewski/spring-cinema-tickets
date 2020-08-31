package com.app.controller;

import com.app.dto.EditProfileDto;
import com.app.mappers.Mapper;
import com.app.model.User;
import com.app.model.thymeleaf.SeanceWithObj;
import com.app.model.thymeleaf.TicketWithObj;
import com.app.service.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final TicketService ticketService;
    private final SeanceService seanceService;
    private final MovieService movieService;
    private final SeatService seatService;

    @GetMapping("/tickets")
    public String profileTickets(Model model, Authentication authentication) {
        User currentUser = userService.findByUsername(authentication.getName());
        List<TicketWithObj> tickets = new ArrayList<>();
        model.addAttribute("seances", seanceService.getAll());
        ticketService.findByUserId(currentUser.getId())
                .stream()
                .forEach(ticket -> {
                    tickets.add(
                            TicketWithObj
                                    .builder()
                                    .id(ticket.getId())
                                    .discount(ticket.getDiscount())
                                    .price(ticket.getPrice())
                                    .date(seanceService.findById(ticket.getSeanceId()).getDateTime())
                                    .movie(movieService.findById(seanceService.findById(ticket.getSeanceId()).getMovieId()))
                                    .user(currentUser)
                                    .placeNumber(seatService.getSeat(ticket.getSeatId()).getPlace())
                                    .rowNumber(seatService.getSeat(ticket.getSeatId()).getRowsNumber())
                                    .build()
                    );
                });
        model.addAttribute("tickets",tickets);
        return "user_tickets";
    }

    @GetMapping("/edit")
    public String editProfile(Model model, Authentication authentication) {
        EditProfileDto editProfileDto = Mapper.fromUserToEditProfileDto(userService.findByUsername(authentication.getName()));
        model.addAttribute("user", editProfileDto);
        return "profile";
    }

    @PostMapping("/edit")
    public String editedProfile (@ModelAttribute EditProfileDto editProfileDto, Model model){
        if (editProfileDto.getNewPassword().isEmpty()){
            editProfileDto.setNewPassword(editProfileDto.getPassword());
        }
        User user = Mapper.fromEditProfileDtoToUser(editProfileDto);
        model.addAttribute("status", (userService.edit(user).getId().equals(user.getId())) ? "User edited!" : "Can't edit user");
        return "admin_operation";
    }
}
