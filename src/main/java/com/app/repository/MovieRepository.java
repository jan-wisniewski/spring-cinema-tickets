package com.app.repository;

import com.app.model.Movie;
import com.app.repository.generic.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
    Optional<Movie> isUniqueMovie(Movie movie);
}
