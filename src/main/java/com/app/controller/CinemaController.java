package com.app.controller;

import com.app.model.Cinema;
import com.app.model.thymeleaf.CinemaWithObj;
import com.app.service.CinemaService;
import com.app.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cinema")
public class CinemaController {

    private final CinemaService cinemaService;
    private final CityService cityService;

    @GetMapping("/all")
    public String getAll(Model model) {
        List<CinemaWithObj> cinemas = new ArrayList<>();
        for (Cinema c : cinemaService.getAll()) {
            cinemas.add(
                    CinemaWithObj
                            .builder()
                            .id(c.getId())
                            .name(c.getName())
                            .city(cityService.findCityById(c.getCityId()))
                            .img(c.getImg())
                            .build()
            );

        }
        model.addAttribute("cinemas", cinemas);
        return "cinemas";
    }

    @GetMapping("/all/city/{id}")
    public String getAllByCityId(@PathVariable Integer id,  Model model) {
        List<CinemaWithObj> cinemas = new ArrayList<>();
        for (Cinema c : cinemaService.getAllInCity(cityService.findCityById(id))) {
            cinemas.add(
                    CinemaWithObj
                            .builder()
                            .id(c.getId())
                            .name(c.getName())
                            .city(cityService.findCityById(c.getCityId()))
                            .img(c.getImg())
                            .build()
            );
        }
        model.addAttribute("cinemas", cinemas);
        return "cinemas";
    }


}
