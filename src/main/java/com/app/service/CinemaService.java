package com.app.service;

import com.app.dto.CreateCinemaDto;
import com.app.dto.CreateCityDto;
import com.app.exception.AdminServiceException;
import com.app.exception.CityServiceException;
import com.app.model.Cinema;
import com.app.model.City;
import com.app.repository.CinemaRepository;
import com.app.repository.CinemaRoomRepository;
import com.app.repository.SeanceRepository;
import com.app.validator.CreateCinemaDtoValidator;
import com.app.validator.CreateCityDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CinemaService {

    private final CinemaRepository cinemaRepository;
    private final SeanceRepository seanceRepository;
    private final CinemaRoomRepository cinemaRoomRepository;

    public Cinema getById(Integer id){
        return cinemaRepository.findById(id).orElseThrow();
    }

    public List<Cinema> getAll() {
        return cinemaRepository.findAll();
    }

    public List<Cinema> getAllInCity(City city) {
        List<Cinema> cinemas = cinemaRepository.findAll();
        List<Cinema> result = new ArrayList<>();
        for(Cinema c : cinemas){
            if(c.getCityId().equals(city.getId())){
            result.add(c);
            }
        }
        return result;
    }

    public Integer deleteCinema(Integer id) {
        Cinema cinema = cinemaRepository.findById(id).orElseThrow();
        if (!seanceRepository.findByCinema(cinema).isEmpty()) {
            System.out.println("Can't delete cinema! Seances will be displayed there!");
            return 0;
        }
        System.out.println("Deleted cinemas rooms associated with this cinema: " +cinemaRoomRepository.deleteAllByCinemaId(cinema));
        return (cinemaRepository.deleteById(cinema.getId())) ? 1 : 0;
    }

    public Integer addCinema(CreateCinemaDto cinemaDto) {
        var validator = new CreateCinemaDtoValidator();
        var errors = validator.validate(cinemaDto);
        if (!errors.isEmpty()) {
            String errorsMessage = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + " : " + e.getValue())
                    .collect(Collectors.joining("\n"));
            throw new AdminServiceException("Add city errors: " + errorsMessage);
        }

        if (cinemaRepository.findByName(cinemaDto.getName()).isPresent()) {
            System.out.println("This city already exists in database");
            return 0;
        }

        Cinema cinemaToAdd = Cinema
                .builder()
                .name(cinemaDto.getName())
                .cityId(cinemaDto.getCityId())
                .build();


        return cinemaRepository.add(cinemaToAdd).isPresent() ? 1 : 0;
    }

    public Cinema findCinemaById(Integer cinemaId) {
        return cinemaRepository.findById(cinemaId).orElseThrow(() -> new CityServiceException("Failed"));
    }

}
