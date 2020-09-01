package com.app.service;

import com.app.dto.CreateTicketDto;
import com.app.enums.SeatState;
import com.app.exception.TicketServiceException;
import com.app.exception.UserServiceException;
import com.app.mappers.Mapper;
import com.app.model.SeatsSeance;
import com.app.model.Ticket;
import com.app.repository.TicketRepository;
import com.app.validator.CreateTicketDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final SeatService seatService;
    private final SeatSeanceService seatSeanceService;

    public SeatsSeance[][] seatsSeancesFromListToArray(List<SeatsSeance> seats) {
        int rows = seatService.getSeatById(seats.get(seats.size() - 1).getSeatId()).getRowsNumber();
        int cols = seatService.getSeatById(seats.get(seats.size() - 1).getSeatId()).getPlace();
        SeatsSeance[][] seatsSeances = new SeatsSeance[rows][cols];
        int idx = 0;
        for (int i = 0; i < seatsSeances.length; i++) {
            for (int j = 0; j < seatsSeances[i].length; j++) {
                seatsSeances[i][j] = seats.get(idx);
                idx++;
            }
        }
        return seatsSeances;
    }

    public Integer buyTicket(CreateTicketDto ticketDto) {
        if (Objects.isNull(ticketDto)) {
            throw new TicketServiceException("Ticket Dto is null");
        }
        var ticketValidator = new CreateTicketDtoValidator();
        var errors = ticketValidator.validate(ticketDto);
        if (!errors.isEmpty()) {
            var messageErrors = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getValue() + " : " + e.getKey())
                    .collect(Collectors.joining("\n"));
            throw new TicketServiceException("Create ticket validation errors: " + messageErrors);
        }
        var ticket = Mapper.fromCreateTicketDtoToTicket(ticketDto);

        var createdTicket = ticketRepository
                .add(ticket)
                .orElseThrow(() -> new UserServiceException("cannot insert ticket to db"));


        SeatsSeance currentSeat = seatSeanceService.getSeatSeancesBySeatId(ticket.getSeatId());
        currentSeat.setState(SeatState.ORDERED);
        seatSeanceService.editSeatSeance(currentSeat);
        return createdTicket.getId();
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket getById(Integer id) {
        return ticketRepository.findById(id).orElseThrow();
    }

    public List<Ticket> findBySeanceId(Integer id) {
        return ticketRepository.findBySeanceId(id);
    }

    public List<Ticket> findByUserId (Integer id){
        return ticketRepository.findByUserId(id);
    }

}