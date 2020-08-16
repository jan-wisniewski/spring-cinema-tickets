package com.app.controller;

import com.app.model.Cinema;
import com.app.model.CinemaRoom;
import com.app.model.thymeleaf.CinemaRoomWithObj;
import com.app.model.thymeleaf.CinemaWithObj;
import com.app.service.CinemaRoomService;
import com.app.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cinemaroom")
public class CinemaRoomController {

    private final CinemaRoomService cinemaRoomService;
    private final CinemaService cinemaService;

    @GetMapping("/delete/{id}")
    public Integer delete(@PathVariable Integer id){
        return cinemaRoomService.deleteCinemaRoom(id);
    }

    @GetMapping("/{id}")
    public CinemaRoom getById(@PathVariable Integer id){
        return cinemaRoomService.findById(id);
    }

    @GetMapping("/all")
    public String getAll(Model model){

        List<CinemaRoomWithObj> cinemaRooms = new ArrayList<>();
        for (CinemaRoom c : cinemaRoomService.getAll()) {
            cinemaRooms.add(
                    CinemaRoomWithObj
                            .builder()
                            .id(c.getId())
                            .name(c.getName())
                            .places(c.getPlaces())
                            .rowsNumber(c.getRowsNumber())
                            .cinema(cinemaService.findCinemaById(c.getCinemaId()))
                            .build()
            );

        }
        model.addAttribute("cinemaRooms", cinemaRooms);
        return "cinemaRooms";
    }

}
