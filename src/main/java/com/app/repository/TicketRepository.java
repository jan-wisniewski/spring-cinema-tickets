package com.app.repository;

import com.app.model.Seance;
import com.app.model.Ticket;
import com.app.repository.generic.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    boolean isPlaceAvailable(Ticket ticket);
    List<Ticket> findBySeanceId (Integer id);
}
