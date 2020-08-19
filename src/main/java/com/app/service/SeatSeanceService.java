package com.app.service;

import com.app.exception.SeatSeanceException;
import com.app.model.Seat;
import com.app.model.SeatsSeance;
import com.app.model.view.SeatsSeanceWithSeanceDate;
import com.app.repository.SeatsSeancesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SeatSeanceService {
    private final SeatsSeancesRepository seatsSeancesRepository;

    public Optional<SeatsSeance> editSeatSeance(SeatsSeance seatsSeance) {
        return seatsSeancesRepository.update(seatsSeance);
    }

    public SeatsSeance getSeatSeancesBySeatId(Integer seatId) {
        return seatsSeancesRepository.findBySeatId(seatId).orElseThrow(() -> new SeatSeanceException("Failed2"));
    }

    public List<SeatsSeance> getSeatsSeancesListBySeanceId(Integer seanceId) {
        return seatsSeancesRepository.findBySeanceId(seanceId);
    }

    public List<SeatsSeance> addAllBySeanceIds(List<Seat> seats, Integer seanceId) {
        return seatsSeancesRepository.addAllBySeanceId(seats, seanceId);
    }

    public List<SeatsSeanceWithSeanceDate> isOneOfAPlaceReservedForFutureSeance(List<Seat> seats) {
        return seatsSeancesRepository.isOneOfAPlaceReservedForFutureSeance(seats);
    }

    public Boolean isAvailableToRemoveSeats(List<Seat> seats) {
        return !isOneOfAPlaceReservedForFutureSeance(seats).isEmpty();
    }

    public String showReservedSeatsForFutureSeance(List<SeatsSeanceWithSeanceDate> reservedSeats) {
        return reservedSeats
                .stream()
                .map(s -> "seat id: " + s.getId() + ", seance date: " + s.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .collect(Collectors.joining("\n"));
    }

    public Integer clearReservedSeats() {
        return seatsSeancesRepository.clearSeatSeances();
    }

    public List<SeatsSeance> findAllBySeanceId (Integer id){
        return seatsSeancesRepository.findBySeanceId(id);
    }

    public List<SeatsSeance> findAllBySeanceIdWithFreeStatus(Integer id) {
        return seatsSeancesRepository.findAllFree(id);
    }
}
