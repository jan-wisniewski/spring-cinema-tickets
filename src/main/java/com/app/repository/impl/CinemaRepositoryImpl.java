package com.app.repository.impl;

import com.app.connection.DbConnection;
import com.app.model.Cinema;
import com.app.repository.CinemaRepository;
import com.app.repository.generic.AbstractCrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Component
public class CinemaRepositoryImpl extends AbstractCrudRepository<Cinema, Integer> implements CinemaRepository {

    private final DbConnection dbConnection;

    public CinemaRepositoryImpl(DbConnection dbConnection) {
        super(dbConnection);
        this.dbConnection = dbConnection;
    }

    @Override
    public Optional<Cinema> findByName(String name) {
        String SQL = "SELECT * FROM cinemas where name = :name";
        var foundCinema = dbConnection
                .getJdbi()
                .withHandle(h -> h.createQuery(SQL)
                        .bind("name", name)
                        .mapToBean(Cinema.class)
                        .findFirst()
                );
        return foundCinema;
    }

    @Override
    public Optional<Cinema> findByCityId(Integer cityId) {
        var sql = """
                select * from cinemas where city_id = :cityId 
                 """;
        return dbConnection
                .getJdbi()
                .withHandle(handle -> handle
                        .createQuery(sql)
                        .bind("cityId", cityId)
                        .mapToBean(Cinema.class)
                        .findFirst()
                );
    }

    @Override
    public Optional<Cinema> findByCinemaRoomId(Integer cinemaRoomId) {
        var sql = """
                select c.id, c.name, c.city_id
                from cinema_rooms cr join cinemas c on cr.cinema_id = c.id where cr.id = :cinemaRoomId;
                """;
        return dbConnection
                .getJdbi()
                .withHandle(handle -> handle
                        .createQuery(sql)
                        .bind("cinemaRoomId", cinemaRoomId)
                        .mapToBean(Cinema.class)
                        .findFirst()
                );
    }
}
