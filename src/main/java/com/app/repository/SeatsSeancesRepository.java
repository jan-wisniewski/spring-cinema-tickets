package com.app.repository;

import com.app.model.Seance;
import com.app.model.Seat;
import com.app.model.SeatsSeance;
import com.app.model.view.SeatsSeanceWithSeanceDate;
import com.app.repository.generic.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatsSeancesRepository extends CrudRepository<SeatsSeance, Integer> {
    Integer addAll(List<Seat> seats, Seance seance);
    Optional<SeatsSeance> findBySeatIdAndSeanceId(Integer seatId, Integer seanceId);
    List<SeatsSeance> findBySeanceId(Integer seanceId);
    List<SeatsSeance> addAllBySeanceId (List<Seat> seats, Integer seanceId);
    List<SeatsSeanceWithSeanceDate> isOneOfAPlaceReservedForFutureSeance (List<Seat> seats);
    Integer clearSeatSeances();
    List<SeatsSeance> findAllFree(Integer id);
}
