package com.app.repository.impl;

import com.app.config.connection.DbConnection;
import com.app.model.Movie;
import com.app.repository.MovieRepository;
import com.app.repository.generic.AbstractCrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepositoryImpl extends AbstractCrudRepository<Movie, Integer> implements MovieRepository {
    private DbConnection dbConnection;

    public MovieRepositoryImpl(DbConnection dbConnection) {
        super(dbConnection);
        this.dbConnection = dbConnection;
    }

    //todo mapowanie -> dto do service przerabianie do modelu i do repo
    @Override
    public Optional<Movie> isUniqueMovie(Movie movie) {
        var SQL = "select * from movies where title=:title and genre=:genre and date_from=:dateFrom and date_to=:dateTo";
        return dbConnection
                .getJdbi()
                .withHandle(handle -> handle
                        .createQuery(SQL)
                        .bind("title", movie.getTitle())
                        .bind("genre", movie.getGenre())
                        .bind("dateFrom", movie.getDateFrom())
                        .bind("dateTo", movie.getDateTo())
                        .mapToBean(Movie.class)
                        .findFirst());
    }

    @Override
    public List<Movie> findAllWithFutureDate() {
        var sql = """
                select * from movies where date_to >=:dateto
                """;
        return dbConnection
                .getJdbi()
                .withHandle(handle -> handle
                        .createQuery(sql)
                        .bind("dateto", LocalDateTime.now())
                        .mapToBean(Movie.class)
                        .list()
                );


    }
}
