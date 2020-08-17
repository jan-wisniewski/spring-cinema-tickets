package com.app.controller;

import com.app.model.Reservation;
import com.app.model.Seance;
import com.app.model.Ticket;
import com.app.model.thymeleaf.SeanceWithObj;
import com.app.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;
    private final SeanceService seanceService;
    private final MovieService movieService;
    private final CinemaRoomService cinemaRoomService;
    private final ReservationService reservationService;
    private final SeatSeanceService seatSeanceService;

    @GetMapping("/seance/{id}")
    public String findById (@PathVariable Integer id, Model model){
        Seance seance = seanceService.findById(id);
        var seanceObj = SeanceWithObj
                .builder()
                .price(seance.getPrice())
                .id(seance.getId())
                .movie(movieService.findById(seance.getMovieId()))
                .dateTime(seance.getDateTime())
                .cinemaRoom(cinemaRoomService.findById(seance.getCinemaRoomId()))
                .build();
        model.addAttribute("seance",seanceObj);
        model.addAttribute("allTickets",seatSeanceService.findAllBySeanceId(seance.getId()));
       // model.addAttribute("tickets",ticketService.findBySeanceId(seance.getId()));
        //model.addAttribute("reservations",reservationService.findBySeanceId(seance.getId()));
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
