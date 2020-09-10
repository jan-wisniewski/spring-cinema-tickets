package com.app.controller;

import com.app.model.City;
import com.app.model.thymeleaf.CityWithObj;
import com.app.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for City entitiy implementing RESTful service
 */
@Controller
//@RestController
@RequiredArgsConstructor
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;


    /***
     * Get method calling CityService deleting City from database based on ID
     * The method returns Integer with value 1 or 0 depending if the deletion was success
     * @param id ID of the city in database
     * @return
     */
    @GetMapping("/delete/{id}")
    public Integer deleteCity(@PathVariable Integer id) {
        return cityService.deleteCity(id);
    }

    /***
     * Get method calling CityService getting and returning City object based on ID in database
     *
     * @param id ID of the city in database
     * @return
     */
    @GetMapping("/{id}")
    public City showById(@PathVariable Integer id) {
        return cityService.findCityById(id);
    }


    /***
     * Get method calling CityService and getting all the City object as a ArrayList and providing it via Model interface
     *
     * @param model Model interface
     * @return
     */
    @GetMapping("/all")
    public String getAll(Model model) {
        List<CityWithObj> cities = new ArrayList<>();
        for (City c : cityService.getAll()) {
            cities.add(CityWithObj.builder()
                    .id(c.getId())
                    .name(c.getName())
                    .img(c.getImg())
                    .build());
        }
        model.addAttribute("cities", cities);
        return "cities";
    }

}
