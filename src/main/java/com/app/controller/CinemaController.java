package com.app.controller;

import com.app.model.Cinema;
import com.app.service.CinemaService;
import com.app.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cinema")
public class CinemaController {

    private final CinemaService cinemaService;
    private final CityService cityService;

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        var cinema = cinemaService.getById(id);
        model.addAttribute("cinema", cinema);
        model.addAttribute("city",cityService.findCityById(cinema.getCityId()));
        return "cinema";
    }

    @GetMapping("/all")
    public List<Cinema> getAll() {
        return cinemaService.getAll();
    }

    @GetMapping("/delete/{id}")
    public Integer deleteById(@PathVariable Integer id) {
        return cinemaService.deleteCinema(id);
    }

}
