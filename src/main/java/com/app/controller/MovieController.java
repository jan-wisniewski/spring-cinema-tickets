package com.app.controller;

import com.app.model.Movie;
import com.app.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/delete/{id}")
    public Integer deleteById(@PathVariable Integer id) {
        return movieService.deleteMovie(id);
    }

    @GetMapping("/{id}")
    public Movie getById(@PathVariable Integer id) {
        return movieService.findById(id);
    }

    @GetMapping("/all")
    public List<Movie> getAll (){
        return movieService.getAll();
    }

}
