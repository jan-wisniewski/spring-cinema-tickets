package com.app.controller;

import com.app.model.Cinema;
import com.app.model.CinemaRoom;
import com.app.model.Movie;
import com.app.model.Seance;
import com.app.model.thymeleaf.SeanceWithObj;
import com.app.service.*;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {

    private SeanceService seanceService;
    private final MovieService movieService;
    private final CinemaRoomService cinemaRoomService;
    private final CinemaService cinemaService;
    private final CityService cityService;

    @GetMapping("/cinema/{id}/seance")
    public String showSeancesInCinema(@PathVariable Integer id, Model model) {
        List<SeanceWithObj> seanceWithObjs = new ArrayList<>();
        System.out.println(cinemaRoomService.getCinemaRoomListByCinemaId(id));
        System.out.println("------------------------------------");
        seanceService.getSeancesListByCinemaRooms(cinemaRoomService.getCinemaRoomListByCinemaId(id))
                .forEach(seance -> {
                    seanceWithObjs.add(
                            SeanceWithObj.builder()
                                    .cinemaRoom(cinemaRoomService.findById(seance.getCinemaRoomId()))
                                    .dateTime(seance.getDateTime())
                                    .movie(movieService.findById(seance.getMovieId()))
                                    .price(seance.getPrice())
                                    .id(seance.getId())
                                    .build()
                    );
                });
        model.addAttribute("seances", seanceWithObjs);
        model.addAttribute("cinema", cinemaService.getById(id));
        return "seancesInCinema";
    }

    @GetMapping("/city/{id}/cinema")
    public String showCinemasWithCityId(@PathVariable Integer id, Model model) {
        Set<Cinema> cinema = cinemaRoomService.getCinemaRoomListByCityId(id)
                .stream()
                .map(cr -> cinemaService.getById(cr.getCinemaId()))
                .collect(Collectors.toSet());
        model.addAttribute("cinemas", cinema);
        model.addAttribute("city", cityService.findCityById(id));
        return "city";
    }

    @GetMapping("/{phrase}")
    public String search(Model model, @PathVariable String phrase) {
        List<SeanceWithObj> seances = new ArrayList<>();
        for (Seance s : seanceService.findByPhrase(phrase)) {
            seances.add(
                    SeanceWithObj
                            .builder()
                            .id(s.getId())
                            .cinemaRoom(cinemaRoomService.findById(s.getCinemaRoomId()))
                            .dateTime(s.getDateTime())
                            .movie(movieService.findById(s.getMovieId()))
                            .price(s.getPrice())
                            .build()
            );
        }
        //model.addAttribute("phrase",phrase);
        model.addAttribute("seances", seances);
        System.out.println(seances);
        return "search";
    }

}
