package com.app.controller;

import com.app.model.City;
import com.app.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RestController
@RequiredArgsConstructor
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;

    @GetMapping("/delete/{id}")
    public Integer deleteCity (@PathVariable Integer id){
        return cityService.deleteCity(id);
    }

    @GetMapping("/show/{id}")
    public City showById (@PathVariable Integer id){
        return cityService.findCityById(id);
    }

    @GetMapping("/all")
    public List<City> getAll(){
        return cityService.getAll();
    }

    @GetMapping("/add")
    public String addCity (){
        return "addcity";
    }

/*    @PostMapping("/add")
    public String add(){

    }*/

}
