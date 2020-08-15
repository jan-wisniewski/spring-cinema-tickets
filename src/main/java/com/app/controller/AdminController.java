package com.app.controller;

import com.app.model.Cinema;
import com.app.model.thymeleaf.CinemaWithObj;
import com.app.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private CinemaService cinemaService;
    private CinemaRoomService cinemaRoomService;
    private CityService cityService;
    private MovieService movieService;
    private ReservationService reservationService;
    private SeanceService seanceService;
    private SeatService seatService;
    private TicketService ticketService;
    private UserService userService;

    @GetMapping("")
    public String index() {
        return "admin";
    }


    //--------------[CINEMA]-----------------------------------

    @GetMapping("/cinema")
    public String cinemas(Model model) {
        List<CinemaWithObj> cinemaWithObjs =
                cinemaService
                        .getAll()
                        .stream()
                        .map(cinema -> CinemaWithObj
                                .builder()
                                .id(cinema.getId())
                                .city(cityService.findCityById(cinema.getCityId()))
                                .name(cinema.getName())
                                .build())
                        .collect(Collectors.toList());
        model.addAttribute("cinemas", cinemaWithObjs);
        return "admin_cinemas";
    }

    @GetMapping("/cinema/delete/{id}")
    public String deleteCinema(@PathVariable Integer id, Model model) {
        model.addAttribute("status",(cinemaService.deleteCinema(id)==1) ? "Cinema deleted!" : "Cant' delete cinema");
        return "admin_operation";
    }

    //--------------[CINEMA ROOM]-----------------------------------

    @GetMapping("/room/delete/{id}")
    public Integer deleteRoom(@PathVariable Integer id) {
        return cinemaRoomService.deleteCinemaRoom(id);
    }

    //--------------[CITY]-----------------------------------

    @GetMapping("/city/delete/{id}")
    public Integer deleteCity(@PathVariable Integer id) {
        return cityService.deleteCity(id);
    }

    //--------------[Movie]-----------------------------------

    @GetMapping("/movie/delete/{id}")
    public Integer deleteMovie(@PathVariable Integer id) {
        return movieService.deleteMovie(id);
    }

    //--------------[SEANCE]-----------------------------------

    @GetMapping("/seance/delete/{id}")
    public Integer deleteSeance(@PathVariable Integer id) {
        return seanceService.deleteSeance(id);
    }

    //--------------[SEAT]-----------------------------------

    @GetMapping("/seat/delete/{id}")
    public Integer deleteSeat(@PathVariable Integer id) {
        return seatService.deleteById(id);
    }

    //--------------[USER]-----------------------------------

    @GetMapping("/user/delete/{id}")
    public Integer deleteUser(@PathVariable Integer id) {
        return (userService.delete(id)) ? 1 : 0;
    }

}
