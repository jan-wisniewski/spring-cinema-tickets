package com.app.controller;

import com.app.model.Cinema;
import com.app.service.CinemaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
    public String getAll(){
        String cinemas = cinemaService
                .getAll()
                .stream()
                .map(Cinema::getName)
                .collect(Collectors.joining("\n"));
      return "<html><head><title>ALl cinemas</title></head><body><h1>Cinemas:</h1>"+cinemas+"</body></html>";
    }

    @GetMapping("/cinema/delete/{id}")
    public Integer deleteById(@PathVariable Integer id){
        return cinemaService.deleteCinema(id);
    }

}
