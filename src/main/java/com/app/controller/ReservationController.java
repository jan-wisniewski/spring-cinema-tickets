package com.app.controller;

import com.app.model.Reservation;
import com.app.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/show/{id}")
    public Reservation findById (@PathVariable Integer id){
        return reservationService.getById(id);
    }

    @GetMapping("/all")
    public List<Reservation> getAll (){
        return reservationService.getAll();
    }

    @GetMapping("/delete/{id}")
    public Integer deleteById (@PathVariable Integer id){
        return (reservationService.deleteReservation(id)) ? 1 : 0;
    }

}
