package com.app.controller;

import com.app.model.Seance;
import com.app.model.Seat;
import com.app.service.SeanceService;
import com.app.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seat")
public class SeatController {
    private final SeatService seatService;

    @GetMapping("/{id}")
    public Seat findById(@PathVariable Integer id) {
        return seatService.getSeatById(id);
    }

    @GetMapping("/all")
    public List<Seat> getAll() {
        return seatService.getAll();
    }

    @GetMapping("/delete/{id}")
    public Integer deleteById(@PathVariable Integer id) {
        return seatService.deleteById(id);
    }
}
