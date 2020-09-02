package com.app.controller;

import com.app.model.Seance;
import com.app.model.thymeleaf.SeanceWithObj;
import com.app.service.*;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/seance")
public class SeanceController {
    private final SeanceService seanceService;
    private final MovieService movieService;
    private final CinemaRoomService cinemaRoomService;
    private final CinemaService cinemaService;
    private final CityService cityService;

    @GetMapping("/all")
    public String getAll (Model model){
        List<SeanceWithObj> seances = new ArrayList<>();
        for (Seance s : seanceService.getAll()){
            seances.add(
                    SeanceWithObj
                            .builder()
                            .id(s.getId())
                            .cinemaRoom(cinemaRoomService.findById(s.getCinemaRoomId()))
                            .dateTime(s.getDateTime())
                            .movie(movieService.findById(s.getMovieId()))
                            .price(s.getPrice())
                            .img(movieService.findById(s.getMovieId()).getImg())
                            .cinemaName(cinemaService.findCinemaById(cinemaRoomService.findById(s.getCinemaRoomId()).getCinemaId()).getName())
                            .cityName(cityService.findCityById(cinemaService.findCinemaById(cinemaRoomService.findById(s.getCinemaRoomId()).getCinemaId()).getCityId()).getName())
                            .build()
            );
        }
        model.addAttribute("seances",seances);
        return "seances";
    }

    @GetMapping("/delete/{id}")
    public Integer deleteById(@PathVariable Integer id){
        return seanceService.deleteSeance(id);
    }

}
