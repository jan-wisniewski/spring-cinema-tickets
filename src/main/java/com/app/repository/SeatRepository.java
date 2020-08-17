package com.app.repository;

import com.app.model.CinemaRoom;
import com.app.model.Seat;
import com.app.repository.generic.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Integer> {
    List<Seat> addAll(List<Seat> seatList);

    List<Seat> findAllByCinemaId(CinemaRoom cinemaRoom);

    Optional<Seat> findByRowAndPlaceAtCinemaRoom(Integer row, Integer place, Integer cinemaRoomId);

    List<Seat> findByPlacesAboveSeatPlace(CinemaRoom cinemaRoom, Integer seatPlaceNumber);

    List<Seat> findByPlacesAboveSeatRow(CinemaRoom cinemaRoom, Integer seatRowNumber);

    Integer deleteAll(List<Seat> seats);

}
