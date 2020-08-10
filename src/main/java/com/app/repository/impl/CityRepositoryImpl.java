package com.app.repository.impl;

import com.app.connection.DbConnection;
import com.app.model.City;
import com.app.repository.CityRepository;
import com.app.repository.generic.AbstractCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CityRepositoryImpl extends AbstractCrudRepository<City, Integer> implements CityRepository {

    private DbConnection dbConnection;

    public CityRepositoryImpl(DbConnection dbConnection) {
        super(dbConnection);
        this.dbConnection = dbConnection;
    }


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
