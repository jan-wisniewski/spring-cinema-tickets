package com.app.controller;

import com.app.model.Cinema;
import com.app.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cinema")
public class CinemaController {

    private final CinemaService cinemaService;

    @GetMapping("/{id}")
    public Cinema getById(@PathVariable Integer id){
        return cinemaService.getById(id);
    }

    @GetMapping("/all")
    public List<Cinema> getAll(){
        return cinemaService.getAll();
    }

    @GetMapping("/delete/{id}")
    public Integer deleteById(@PathVariable Integer id){
        return cinemaService.deleteCinema(id);
    }

}
