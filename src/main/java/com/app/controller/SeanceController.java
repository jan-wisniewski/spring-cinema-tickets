package com.app.controller;

import com.app.model.Seance;
import com.app.service.SeanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seance")
public class SeanceController {
    private final SeanceService seanceService;

    @GetMapping("/{id}")
    public Seance findById (@PathVariable Integer id){
        return seanceService.findById(id);
    }

    @GetMapping("/all")
    public List<Seance> getAll (){
        return seanceService.getAll();
    }

    @GetMapping("/delete/{id}")
    public Integer deleteById(@PathVariable Integer id){
        return seanceService.deleteSeance(id);
    }

}
