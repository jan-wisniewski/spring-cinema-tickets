package com.app.controller;

import com.app.dto.CreateCinemaDto;
import com.app.dto.CreateCinemaRoomDto;
import com.app.dto.CreateCityDto;
import com.app.model.Cinema;
import com.app.model.CinemaRoom;
import com.app.model.City;
import com.app.model.thymeleaf.CinemaRoomWithObj;
import com.app.model.thymeleaf.CinemaWithObj;
import com.app.model.thymeleaf.CityWithObj;
import com.app.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("newCinema", new Cinema());
        List<City> cities = cityService.getAll();
        model.addAttribute("getAllCities", cities);
        return "admin_cinemas";
    }

    @GetMapping("/cinema/delete/{id}")
    public String deleteCinema(@PathVariable Integer id, Model model) {
        model.addAttribute("status",(cinemaService.deleteCinema(id)==1) ? "Cinema deleted!" : "Cant' delete cinema. You can't have any active seances!");
        return "admin_operation";
    }

    @PostMapping("cinema/add")
    public String addCinema(@ModelAttribute Cinema cinema, Model model) {
        model.addAttribute("status",(cinemaService.addCinema(new CreateCinemaDto(cinema.getName(), cinema.getCityId()))==1) ? "Cinema added!" : "Cant' add Cinema. Duplicate name");
        System.out.println(cinema.toString());
        return "admin_operation";
    }

    //--------------[CINEMA ROOM]-----------------------------------

    @GetMapping("/cinemaRoom")
    public String cinemaRooms(Model model) {
        List<CinemaRoomWithObj> cinemaRoomWithObjs =
                cinemaRoomService
                        .getAll()
                        .stream()
                        .map(cinemaRoom -> CinemaRoomWithObj
                                .builder()
                                .id(cinemaRoom.getId())
                                .name(cinemaRoom.getName())
                                .places(cinemaRoom.getPlaces())
                                .rowsNumber(cinemaRoom.getRowsNumber())
                                .cinema(cinemaService.findCinemaById(cinemaRoom.getCinemaId()))
                                .build())
                        .collect(Collectors.toList());
        model.addAttribute("cinemasRooms", cinemaRoomWithObjs);
        model.addAttribute("newCinemaRoom", new CinemaRoom());
        List<City> cities = cityService.getAll();
        model.addAttribute("getAllCities", cities);
        List<Cinema> cinemas = cinemaService.getAll();
        model.addAttribute("getAllCinemas", cinemas);
        return "admin_cinema_rooms";
    }



    @GetMapping("/cinemaRoom/delete/{id}")
    public String deleteRoom(@PathVariable Integer id, Model model) {
        model.addAttribute("status",(cinemaRoomService.deleteCinemaRoom(id)==1) ? "Cinema room deleted!" : "Cant' delete cinema room. You can't have any active seances!");
        return "admin_operation";
    }

    @PostMapping("cinemaRoom/add")
    public String addCinemaRoom(@ModelAttribute CinemaRoom cinemaRoom, Model model) {
        model.addAttribute("status",(cinemaRoomService.addCinemaRoom(new CreateCinemaRoomDto(cinemaRoom.getName(), cinemaRoom.getCinemaId(), cinemaRoom.getRowsNumber(), cinemaRoom.getPlaces()))==1) ? "Cinema room added!" : "Cant' add Cinema room. Duplicate name");
        System.out.println(cinemaRoom.toString());
        return "admin_operation";
    }

    //--------------[CITY]-----------------------------------


    @GetMapping("/city")
    public String cities(Model model) {
        List<CityWithObj> cityWithObjs =
                cityService
                        .getAll()
                        .stream()
                        .map(city -> CityWithObj
                                .builder()
                                .id(city.getId())
                                .name(city.getName())
                                .build())
                        .collect(Collectors.toList());
        model.addAttribute("cities", cityWithObjs);
        model.addAttribute("newCity", new City());
        return "admin_cities";
    }

    @GetMapping("/city/delete/{id}")
    public String deleteCity(@PathVariable Integer id, Model model) {
        model.addAttribute("status",(cityService.deleteCity(id)==1) ? "City deleted!" : "Cant' delete city. You need to remove all cinemas first");
        return "admin_operation";
    }


    @PostMapping("city/add")
    public String deleteCity(@ModelAttribute City city, Model model) {
        model.addAttribute("status",(cityService.addCity(new CreateCityDto(city.getName()))==1) ? "City added!" : "Cant' add city. Duplicate name");
        System.out.println(city.toString());
        return "admin_operation";
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
