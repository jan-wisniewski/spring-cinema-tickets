package com.app.service;

import com.app.enums.SeatState;
import com.app.exception.ReservationServiceException;
import com.app.model.Reservation;
import com.app.model.SeatsSeance;
import com.app.model.view.ReservationWithUser;
import com.app.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeatSeanceService seatSeanceService;

    public Integer clearReservations(Integer minutes) {
        return reservationRepository.deleteIfLessThanMinutes(minutes);
    }

    public List<ReservationWithUser> showReservationsListByEmail(String email) {
        return reservationRepository.findByEmail(email);
    }

    public Reservation getById(Integer reservationId) {
        return reservationRepository.findById(reservationId).orElseThrow(() -> new ReservationServiceException("Failed"));
    }

    public Optional<Reservation> addReservation(Reservation reservation) {
        SeatsSeance currentSeat = seatSeanceService.getSeatSeancesBySeatId(reservation.getSeatId());
        currentSeat.setState(SeatState.RESERVED);
        seatSeanceService.editSeatSeance(currentSeat);
        return reservationRepository.add(reservation);
    }

    public boolean deleteReservation(Integer reservationId) {
        return reservationRepository.deleteById(reservationId);
    }

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    public List<Reservation> findBySeanceId (Integer id){
        return reservationRepository.findBySeanceId(id);
    }

}
