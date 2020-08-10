package com.app.controller;

import com.app.model.CinemaRoom;
import com.app.service.CinemaRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cinemaroom")
public class CinemaRoomController {

    private final CinemaRoomService cinemaRoomService;

    @GetMapping("/delete/{id}")
    public Integer delete(@PathVariable Integer id){
        return cinemaRoomService.deleteCinemaRoom(id);
    }

    @GetMapping("/show/{id}")
    public CinemaRoom getById(@PathVariable Integer id){
        return cinemaRoomService.findById(id);
    }

    @GetMapping("/all")
    public List<CinemaRoom> getAll(){
        return cinemaRoomService.getAll();
    }

}
