package com.app.controller;

import com.app.model.Cinema;
import com.app.model.City;
import com.app.model.thymeleaf.CinemaWithObj;
import com.app.model.thymeleaf.CityWithObj;
import com.app.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RestController
@RequiredArgsConstructor
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;

    @GetMapping("/delete/{id}")
    public Integer deleteCity(@PathVariable Integer id) {
        return cityService.deleteCity(id);
    }

    @GetMapping("/{id}")
    public City showById(@PathVariable Integer id) {
        return cityService.findCityById(id);
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        List<CityWithObj> cities = new ArrayList<>();
        for (City c : cityService.getAll()) {
            cities.add(CityWithObj.builder()
                    .id(c.getId())
                    .name(c.getName())
                    .build());
        }
        model.addAttribute("cities", cities);
        return "cities";
    }

}
