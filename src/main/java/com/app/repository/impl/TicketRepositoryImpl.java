package com.app.repository.impl;

import com.app.config.connection.DbConnection;
import com.app.model.Seance;
import com.app.model.SeatsSeance;
import com.app.model.Ticket;
import com.app.repository.TicketRepository;
import com.app.repository.generic.AbstractCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TicketRepositoryImpl extends AbstractCrudRepository<Ticket, Integer> implements TicketRepository {

    private DbConnection dbConnection;

    public TicketRepositoryImpl(DbConnection dbConnection) {
        super(dbConnection);
        this.dbConnection = dbConnection;
    }

    @Override
    public boolean isPlaceAvailable(Ticket ticket) {
        var seatSeance = dbConnection
                .getJdbi()
                .withHandle(handle -> handle
                        .createQuery("select * from seats_seances where seat_id = :seatId AND seance_id = :seanceId AND seat_state = 'FREE'")
                        .bind("seatId", ticket.getSeatId())
                        .bind("seanceId", ticket.getSeanceId())
                        .mapToBean(SeatsSeance.class)
                        .findFirst()
                );
        return seatSeance.isEmpty();
    }

    @Override
    public List<Ticket> findBySeanceId(Integer id) {
        var sql = """
                select t.id, t.seance_id,t.seat_id,t.price,t.discount,t.user_id
                from tickets t join seances s on t.seance_id = s.id
                where seance_id = :seanceId and s.date_time > :date
                """;
        return dbConnection
                .getJdbi()
                .withHandle(handle -> handle
                        .createQuery(sql)
                        .bind("seanceId",id)
                        .bind("date", LocalDateTime.now())
                        .mapToBean(Ticket.class)
                        .list()
                );
    }

    @Override
    public List<Ticket> findByUserId(Integer id) {
        var sql = """
                select * from tickets where user_id = :userId;
                """;
        return dbConnection
                .getJdbi()
                .withHandle(handle -> handle
                        .createQuery(sql)
                        .bind("userId",id)
                        .mapToBean(Ticket.class)
                        .list()
                );
    }
}
