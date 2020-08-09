package com.app.service;

import com.app.persistence.model.Cinema;
import com.app.repository.CinemaRepository;
import com.app.repository.CinemaRoomRepository;
import com.app.repository.SeanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaService {

    private final CinemaRepository cinemaRepository;
    private final SeanceRepository seanceRepository;
    private final CinemaRoomRepository cinemaRoomRepository;

    public CinemaService(CinemaRepository cinemaRepository, SeanceRepository seanceRepository, CinemaRoomRepository cinemaRoomRepository) {
        this.cinemaRepository = cinemaRepository;
        this.seanceRepository = seanceRepository;
        this.cinemaRoomRepository = cinemaRoomRepository;
    }

    public Cinema getById(Integer id){
        return cinemaRepository.findById(id).orElseThrow();
    }

    public List<Cinema> getAll() {
        return cinemaRepository.findAll();
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

}
