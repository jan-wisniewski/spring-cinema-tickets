package com.app.repository;

import com.app.persistence.model.Cinema;
import com.app.persistence.model.CinemaRoom;
import com.app.repository.generic.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CinemaRoomRepository extends CrudRepository<CinemaRoom, Integer> {
    Optional<CinemaRoom> findByNameAndCinemaId(String name, Integer cinemaId);

    List<CinemaRoom> findByCityId(Integer cityId);

    List<CinemaRoom> findByCinemaId(Integer cinemaId);

    Integer deleteAllByCinemaId (Cinema cinema);

    List<CinemaRoom> findByMovieId(Integer movieId);
}
