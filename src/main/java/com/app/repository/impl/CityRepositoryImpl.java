package com.app.repository.impl;

import com.app.config.connection.DbConnection;
import com.app.model.City;
import com.app.repository.CityRepository;
import com.app.repository.generic.AbstractCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Base class for implementing CityRepository interface
 */
@Repository
public class CityRepositoryImpl extends AbstractCrudRepository<City, Integer> implements CityRepository {

    private DbConnection dbConnection;

    /***
     * Constructor for the class allocating database connection into local variable
     * @param dbConnection
     */
    public CityRepositoryImpl(DbConnection dbConnection) {
        super(dbConnection);
        this.dbConnection = dbConnection;
    }


    /***
     * implementation of findByName method from CityRepository
     * Finds cities by name in database
     * @param cityName
     * @return
     */
    @Override
    public Optional<City> findByName(String cityName) {
        var sql = """
                select * from cities where name = :cityName;
                """;
        return dbConnection
                .getJdbi()
                .withHandle(handle -> handle
                        .createQuery(sql)
                        .bind("cityName", cityName)
                        .mapToBean(City.class)
                        .findFirst()
                );
    }
}
