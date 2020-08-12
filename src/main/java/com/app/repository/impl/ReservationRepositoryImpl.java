package com.app.repository.impl;

import com.app.config.connection.DbConnection;
import com.app.model.Reservation;
import com.app.model.view.ReservationWithUser;
import com.app.repository.ReservationRepository;
import com.app.repository.generic.AbstractCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReservationRepositoryImpl extends AbstractCrudRepository<Reservation, Integer> implements ReservationRepository {

    private DbConnection dbConnection;

    public ReservationRepositoryImpl(DbConnection dbConnection) {
        super(dbConnection);
        this.dbConnection = dbConnection;
    }

    @Override
    public Integer deleteIfLessThanMinutes(Integer minutes) {
        var sql = """
                delete r from reservations r
                join seances s on r.seance_id = s.id
                where :checkingDate > date_time;
                """;
        var sql2 = """
                update seats_seances ss join seances s on ss.seance_id = s.id
                set state='FREE' where :checkingDate > date_time and state = 'RESERVED';
                """;
        Integer a = dbConnection
                .getJdbi()
                .withHandle(handle -> handle
                        .createUpdate(sql2)
                        .bind("checkingDate", LocalDateTime.now().plusMinutes(minutes))
                        .execute()
                );
        return dbConnection
                .getJdbi()
                .withHandle(handle -> handle
                        .createUpdate(sql)
                        .bind("checkingDate", LocalDateTime.now().plusMinutes(minutes))
                        .execute()
                );
    }

    @Override
    public List<Reservation> findBySeanceId(Integer seanceId) {
        var sql = """
                select * from reservations where seance_id = :seance_id;
                """;
        return dbConnection
                .getJdbi()
                .withHandle(handle -> handle
                        .createQuery(sql)
                        .bind("seance_id", seanceId)
                        .mapToBean(Reservation.class)
                        .list()
                );
    }

    @Override
    public List<ReservationWithUser> findByEmail(String email) {
        var sql = """
                 select r.id as reservationId, r.seance_id,r.user_id,r.seat_id,u.email as userEmail
                 from reservations r JOIN users u on r.user_id = u.id where u.email = :email;
                """;
        return dbConnection
                .getJdbi()
                .withHandle(handle -> handle
                        .createQuery(sql)
                        .bind("email", email)
                        .mapToBean(ReservationWithUser.class)
                        .list()
                );
    }
}
