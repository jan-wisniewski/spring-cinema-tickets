package com.app.controller;

import com.app.model.Cinema;
import com.app.service.CinemaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class CinemaController {

    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/cinema/{id}")
    public Cinema getById(@PathVariable Integer id){
        return cinemaService.getById(id);
    }

    @GetMapping("/cinema/getAll")
    public List<Cinema> getAll(){
        return cinemaService.getAll();
    }

    @GetMapping("/cinema/delete/{id}")
    public Integer deleteById(@PathVariable Integer id){
        return cinemaService.deleteCinema(id);
    }

}
