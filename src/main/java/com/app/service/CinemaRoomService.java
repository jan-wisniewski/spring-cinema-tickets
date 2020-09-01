package com.app.service;

import com.app.dto.CreateCinemaRoomDto;
import com.app.exception.AdminServiceException;
import com.app.exception.CinemaRoomException;
import com.app.mappers.Mapper;
import com.app.model.CinemaRoom;
import com.app.repository.CinemaRoomRepository;
import com.app.repository.SeanceRepository;
import com.app.validator.CreateCinemaRoomDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CinemaRoomService {

    private final CinemaRoomRepository cinemaRoomRepository;
    private final SeatService seatService;
    private final SeanceRepository seanceRepository;

    public CinemaRoom editCinemaRoom(CinemaRoom cinemaRoom) {
        return cinemaRoomRepository.update(cinemaRoom).orElseThrow();
    }

    public Integer deleteCinemaRoom(Integer id) {
        CinemaRoom cinemaRoom = cinemaRoomRepository.findById(id).orElseThrow();
        if (!seanceRepository.findFutureSeancesAtCinemaRoom(cinemaRoom.getId()).isEmpty()) {
            System.out.println("Can't delete this cinema room! At this cinema room movie will be displayed");
            return 0;
        }
        return (cinemaRoomRepository.deleteById(cinemaRoom.getId())) ? 1 : 0;
    }

    public Integer addCinemaRoom(CreateCinemaRoomDto cinemaRoomDto) {
        if (Objects.isNull(cinemaRoomDto)) {
            throw new AdminServiceException("CinemaRoomDto is null");
        }

        var validator = new CreateCinemaRoomDtoValidator();
        var errors = validator.validate(cinemaRoomDto);

        if (!errors.isEmpty()) {
            String errorsMessage = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + " : " + e.getValue())
                    .collect(Collectors.joining("\n"));
            throw new AdminServiceException("Add cinema room errors: " + errorsMessage);
        }

        if (cinemaRoomRepository.findByNameAndCinemaId(cinemaRoomDto.getName(), cinemaRoomDto.getCinemaId()).isPresent()) {
            throw new AdminServiceException("Cinema room with this name is already on this cinema id");
        }

        var cinemaRoomToAdd = Mapper.fromCinemaRoomDtoToCinemaRoom(cinemaRoomDto);

        var addedCinemaRoom = cinemaRoomRepository
                .add(cinemaRoomToAdd)
                .orElseThrow(() -> new AdminServiceException("Cannot insert to db"));

        if (seatService.addPlacesToNewRows(addedCinemaRoom, 1, addedCinemaRoom.getRowsNumber()).size() == 0) {
            throw new AdminServiceException("Failed to create seats for cinemaRoom!");
        }

        return 1;
    }

    public String getNameByCinemaRoomId(Integer cinemaRoomId) {
        return cinemaRoomRepository.findById(cinemaRoomId).orElseThrow(() -> new CinemaRoomException("Failed")).getName();
    }

    public CinemaRoom findById(Integer cinemaRoomId) {
        return cinemaRoomRepository.findById(cinemaRoomId).orElseThrow();
    }

    public Integer getRowsNumberByCinemaRoomId(Integer cinemaRoomId) {
        return cinemaRoomRepository.findById(cinemaRoomId).orElseThrow(() -> new CinemaRoomException("Failed")).getRowsNumber();
    }

    public Integer getPlacesNumberInRowByCinemaRoomId(Integer cinemaRoomId) {
        return cinemaRoomRepository.findById(cinemaRoomId).orElseThrow(() -> new CinemaRoomException("Failed")).getPlaces();
    }

    public CinemaRoom getCinemaRoomByCinemaRoomId(Integer cinemaRoomId) {
        return cinemaRoomRepository.findById(cinemaRoomId).orElseThrow(() -> new CinemaRoomException("Failed"));
    }

    public List<CinemaRoom> getCinemaRoomListByCinemaId(Integer cinemaId) {
        return cinemaRoomRepository.findByCinemaId(cinemaId);
    }

    public List<CinemaRoom> getCinemaRoomListByCityId(Integer cityId) {
        return cinemaRoomRepository.findByCityId(cityId);
    }

    public String showAllCinemasRooms() {
        return cinemaRoomRepository.findAll()
                .stream()
                .map(cinemaRoom -> cinemaRoom.getId() + ". " + cinemaRoom.getName())
                .collect(Collectors.joining("\n"));
    }

    public List<CinemaRoom> getAll() {
        return cinemaRoomRepository.findAll();
    }

    public List<CinemaRoom> getCinemaRoomListByMovieId(int movieId) {
        return cinemaRoomRepository.findByMovieId(movieId);
    }
}
