package com.app.repository;
import com.app.model.City;
import com.app.repository.generic.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {
    Optional<City> findByName (String cityName);
}
