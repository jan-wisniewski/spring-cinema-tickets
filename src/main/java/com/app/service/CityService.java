package com.app.service;

import com.app.dto.CreateCityDto;
import com.app.exception.AdminServiceException;
import com.app.exception.CityServiceException;
import com.app.model.City;
import com.app.repository.CinemaRepository;
import com.app.repository.CityRepository;
import com.app.validator.CreateCityDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The class is responsible for encapsulating business logic for City CRUD operations
 */
@RequiredArgsConstructor
@Service
public class CityService {

    /**
     *Injection of CityRepository interface
     */
    private final CityRepository cityRepository;
    /**
     *Injection of CinemaRepository interface
     */
    private final CinemaRepository cinemaRepository;

    /***
     *The method updates the City entity in database using CityRepository interface
     * If the update fails for reason not existing City in database the NoSuchElementException will be thrown
     * @param city object of the City which will be updated in this method
     * @return
     */
    public City editCity(City city) {
        return cityRepository.update(city).orElseThrow();
    }

    /***
     *The method deletes a City entity from database
     * It locates the entity based on the argument ID
     * The method checks via CinemaRepository if there is any Cinema linked to the City
     * If there is any Cinema in the City the error is printed in logs and method returns 0
     *
     * @param id ID of the city in database
     * @return
     */
    public Integer deleteCity(Integer id) {
        City city = cityRepository.findById(id).orElseThrow();
        if (!cinemaRepository.findByCityId(city.getId()).isEmpty()) {
            System.out.println("Can't delete city! You need to remove all cinemas first!");
            return 0;
        }
        return (cityRepository.deleteById(city.getId())) ? 1 : 0;
    }

    /***
     *The method searches database for a City with parameter ID and returns its name as a String
     * If the CityRepository can not find the City in database, the new CityServiceException is thrown
     * @param cityId ID of the city in database
     * @return
     */
    public String showNameByCityId(Integer cityId) {
        return cityRepository.findById(cityId).orElseThrow(() -> new CityServiceException("Failed")).getName();
    }

    /***
     *The method searches database for a City with parameter ID and returns the object of a City
     * If the CityRepository can not find the City in database, the new CityServiceException is thrown
     *
     * @param cityId ID of the city in database
     * @return
     */
    public City findCityById(Integer cityId) {
        return cityRepository.findById(cityId).orElseThrow(() -> new CityServiceException("Failed"));
    }

    /***
     *The method returns a List of City objects from database using CityRepository interface
     * @return
     */
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    /***
     *The method Creates new City in database and returns 1 or 0 depending if the CREATE was successfull
     *
     *It validates data provided in cityDto object using CityDtoValidator
     * If there are any errors during validation the new AdminServiceException is thrown with details of the errors
     *
     * If the the City already exists in the database the error message is printed in logs and method returns 0
     *
     * After adding the City to the database, it is checked if the City is present in database and dependeing of its presence the method returns 1 or 0
     * @param cityDto Object for providing data about City which is about to be created
     * @return
     */
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
            System.out.println("This city already exists in database");
            return 0;
        }

        City cityToAdd = City
                .builder()
                .name(cityDto.getName())
                .img(cityDto.getImg())
                .build();

        return cityRepository.add(cityToAdd).isPresent() ? 1 : 0;
    }

}
