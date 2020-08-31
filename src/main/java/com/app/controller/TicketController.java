package com.app.controller;

import com.app.dto.CreateTicketDto;
import com.app.mappers.Mapper;
import com.app.model.*;
import com.app.model.thymeleaf.SeanceWithObj;
import com.app.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;
    private final SeanceService seanceService;
    private final MovieService movieService;
    private final CinemaRoomService cinemaRoomService;
    private final SeatSeanceService seatSeanceService;
    private final ReservationService reservationService;
    private final UserService userService;

    @PostMapping("/seance/buy")
    public String buyTicket (@ModelAttribute CreateTicketDto ticketDto, @RequestParam String ticket_type, Model model){
        String status = "";
        if (ticket_type.equals("student")){
            ticketDto.setDiscount(BigDecimal.valueOf(0.5));
        }
        ticketService.buyTicket(ticketDto);
        status = "You've successfully bought ticket!";
        model.addAttribute("status",status);
        return "admin_operation";
    }

    @GetMapping("/seance/{id}")
    public String findById (@PathVariable Integer id, Model model, Principal principal){
        Seance seance = seanceService.findById(id);
        var seanceObj = SeanceWithObj
                .builder()
                .price(seance.getPrice())
                .id(seance.getId())
                .movie(movieService.findById(seance.getMovieId()))
                .dateTime(seance.getDateTime())
                .cinemaRoom(cinemaRoomService.findById(seance.getCinemaRoomId()))
                .build();
        SeatsSeance[][] seats = ticketService.seatsSeancesFromListToArray(seatSeanceService.findAllBySeanceId(id));
        model.addAttribute("seatSeances",seats);
        model.addAttribute("seance",seanceObj);
        model.addAttribute("freeSeatSeances",seatSeanceService.findAllBySeanceIdWithFreeStatus(seance.getId()));
        model.addAttribute("userId",userService.findByUsername(principal.getName()).getId());
        model.addAttribute("ticket",new CreateTicketDto());
        return "buyticket";
    }

    @GetMapping("/{id}")
    public Ticket findById(@PathVariable Integer id) {
        return ticketService.getById(id);
    }

    @GetMapping("/all")
    public List<Ticket> getAll() {
        return ticketService.findAll();
    }

}
