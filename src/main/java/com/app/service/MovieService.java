package com.app.service;

import com.app.dto.CreateMovieDto;
import com.app.exception.AdminServiceException;
import com.app.exception.MovieServiceException;
import com.app.mappers.Mapper;
import com.app.model.Movie;
import com.app.repository.MovieRepository;
import com.app.repository.SeanceRepository;
import com.app.validator.CreateMovieDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final SeanceRepository seanceRepository;

    public Optional<Movie> editMovie(Movie movie) {
        return movieRepository.update(movie);
    }

    public Integer addMovie(CreateMovieDto movieDto) {
        if (movieDto == null) {
            throw new AdminServiceException("movieDto is null");
        }
        var validator = new CreateMovieDtoValidator();
        var errors = validator.validate(movieDto);
        if (!errors.isEmpty()) {
            String errorsMessage = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + " : " + e.getValue())
                    .collect(Collectors.joining("\n"));
            throw new AdminServiceException("Add movie errors: " + errorsMessage);
        }

        var movieToAdd = Mapper.fromMovieDtoToMovie(movieDto);

        if (movieRepository.isUniqueMovie(movieToAdd).isPresent()) {
            throw new AdminServiceException("This movie with that dateFrom and dateTo is already on db");
        }
        var addedMovie = movieRepository
                .add(movieToAdd)
                .orElseThrow(() -> new AdminServiceException("cannot insert to db"));
        return addedMovie.getId();
    }


    public String showTitle(Integer movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new MovieServiceException("Failed")).getTitle();
    }

    public String showAll() {
        return movieRepository.findAll()
                .stream()
                .map(movie -> movie.getId() + ". " + movie.getTitle())
                .collect(Collectors.joining("\n"));
    }

    public Movie findById(Integer movieId) {
        return movieRepository.findById(movieId).orElseThrow();
    }

    public List<Movie> getAll (){
        return movieRepository.findAll();
    }


    public Integer deleteMovie(Integer id) {
        Movie movie = movieRepository.findById(id).orElseThrow();
        if (!seanceRepository.findByMovie(movie).isEmpty()) {
            System.out.println("Can't delete movie! Movie will be displayed!");
            return 0;
        }
        return (movieRepository.deleteById(movie.getId())) ? 1 : 0;
    }

}
