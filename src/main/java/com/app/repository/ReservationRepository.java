package com.app.repository;

import com.app.model.Reservation;
import com.app.model.view.ReservationWithUser;
import com.app.repository.generic.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
    List<Reservation> findBySeanceId(Integer seanceId);

    List<ReservationWithUser> findByEmail(String email);

    Integer deleteIfLessThanMinutes(Integer minutes);
}
