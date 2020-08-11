package com.app.controller;

import com.app.model.Seat;
import com.app.model.Ticket;
import com.app.service.SeatService;
import com.app.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    @GetMapping("/{id}")
    public Ticket findById(@PathVariable Integer id) {
        return ticketService.getById(id);
    }

    @GetMapping("/all")
    public List<Ticket> getAll() {
        return ticketService.findAll();
    }

}
