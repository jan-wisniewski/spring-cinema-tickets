package com.app.repository;

import com.app.model.*;
import com.app.repository.generic.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeanceRepository extends CrudRepository<Seance, Integer> {
    Optional<Seance> isUniqueSeance(Seance seance);

    List<Seance> findSeancesByCinemaRooms(List<CinemaRoom> cinemaRooms);

    List<Seance> findFutureSeancesAtCinemaRoom(Integer cinemaRoomId);

    Boolean isMovieDisplayed(Seance seance);

    List<Seance> findByMovie(Movie movie);

    List<Seance> findByCinema(Cinema cinema);

    List<Seance> findByCity (City city);

    List<Seance> findByPhrase(String phrase);
}
