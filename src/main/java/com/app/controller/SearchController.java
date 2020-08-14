package com.app.controller;

import com.app.model.CinemaRoom;
import com.app.model.Movie;
import com.app.model.Seance;
import com.app.model.thymeleaf.SeanceWithObj;
import com.app.service.CinemaRoomService;
import com.app.service.MovieService;
import com.app.service.SeanceService;
import lombok.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {

    private SeanceService seanceService;
    private final MovieService movieService;
    private final CinemaRoomService cinemaRoomService;

    @GetMapping("/{phrase}")
    public String search(Model model, @PathVariable String phrase) {
        List<SeanceWithObj> seances = new ArrayList<>();
        System.out.println(phrase);
        System.out.println(seanceService.findByPhrase(phrase));
        for (Seance s : seanceService.findByPhrase(phrase)){
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
        model.addAttribute("seances",seances);
        System.out.println(seances);
        return "search";
    }

}
