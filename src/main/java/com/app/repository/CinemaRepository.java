package com.app.repository;

import com.app.model.Cinema;
import com.app.repository.generic.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaRepository extends CrudRepository<Cinema, Integer> {
    Optional<Cinema> findByName(String name);

    Optional<Cinema> findByCityId(Integer cityId);

    Optional<Cinema> findByCinemaRoomId (Integer cinemaRoomId);
}
