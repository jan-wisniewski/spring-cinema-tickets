package com.app.service;

import com.app.dto.CreateCityDto;
import com.app.exception.AdminServiceException;
import com.app.exception.CityServiceException;
import com.app.model.City;
import com.app.repository.CityRepository;
import com.app.repository.SeanceRepository;
import com.app.validator.CreateCityDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CityService {
    private final CityRepository cityRepository;
    private final SeanceRepository seanceRepository;

    public Optional<City> editCity(City city) {
        return cityRepository.update(city);
    }

    public Integer deleteCity(Integer id) {
        City city = cityRepository.findById(id).orElseThrow();
        if (!seanceRepository.findByCity(city).isEmpty()) {
            System.out.println("Can't delete city! At cinema in this city movie will be displayed");
            return 0;
        }
        return (cityRepository.deleteById(city.getId())) ? 1 : 0;
    }

    public String showNameByCityId(Integer cityId) {
        return cityRepository.findById(cityId).orElseThrow(() -> new CityServiceException("Failed")).getName();
    }

    public String showCities() {
        return cityRepository.findAll()
                .stream()
                .map(city -> city.getId() + ". " + city.getName())
                .collect(Collectors.joining("\n"));
    }

    public City findCityById(Integer cityId) {
        return cityRepository.findById(cityId).orElseThrow(() -> new CityServiceException("Failed"));
    }

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public Integer addCity(CreateCityDto cityDto) {
        var validator = new CreateCityDtoValidator();
        var errors = validator.validate(cityDto);
        if (!errors.isEmpty()) {
            String errorsMessage = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + " : " + e.getValue())
                    .collect(Collectors.joining("\n"));
            throw new AdminServiceException("Add city errors: " + errorsMessage);
        }

        if (cityRepository.findByName(cityDto.getName()).isPresent()) {
            throw new AdminServiceException("This city already exists in database");
        }

        City cityToAdd = City
                .builder()
                .name(cityDto.getName())
                .build();

        cityRepository.add(cityToAdd);
        return cityRepository.findLast().orElseThrow(() -> new AdminServiceException("Failed")).getId();
    }

}