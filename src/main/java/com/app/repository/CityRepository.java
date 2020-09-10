package com.app.repository;
import com.app.model.City;
import com.app.repository.generic.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * CityRepository interface extending generic interface of CrudRepository
 */
@Repository
public interface CityRepository extends CrudRepository<City, Integer> {
    /***
     * declaration of method for finding city by name
     * @param cityName name of searched city as a String
     * @return
     */
    Optional<City> findByName (String cityName);
}
