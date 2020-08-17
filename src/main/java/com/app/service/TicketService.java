package com.app.service;

import com.app.dto.CreateTicketDto;
import com.app.exception.TicketServiceException;
import com.app.exception.UserServiceException;
import com.app.mappers.Mapper;
import com.app.model.Ticket;
import com.app.repository.TicketRepository;
import com.app.validator.CreateTicketDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TicketService {

    private final TicketRepository ticketRepository;

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
                .orElseThrow(() -> new UserServiceException("cannot insert user to db"));
        return createdTicket.getId();
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket getById(Integer id){
        return ticketRepository.findById(id).orElseThrow();
    }

    public List<Ticket> findBySeanceId (Integer id){
        return ticketRepository.findBySeanceId(id);
    }


}