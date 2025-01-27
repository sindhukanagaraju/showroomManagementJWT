package com.example.showroommanagement.controller;

import com.example.showroommanagement.dto.ResponseDTO;
import com.example.showroommanagement.entity.Showroom;
import com.example.showroommanagement.service.ShowroomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/showroom")
public class ShowroomController {
    private final ShowroomService showroomService;

    public ShowroomController(ShowroomService showroomService) {
        this.showroomService = showroomService;
    }

    @PostMapping("/create")
    public ResponseDTO createShowroom(@RequestBody final Showroom Showroom) {
        return this.showroomService.createShowroom(Showroom);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO retrieveShowroomById(@PathVariable final Integer id) {
        return this.showroomService.retrieveShowroomById(id);
    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieveShowroom() {
        return this.showroomService.retrieveShowroom();
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateShowroom(@PathVariable final Integer id, @RequestBody final Showroom Showroom) {
        return this.showroomService.updateShowroomById(Showroom, id);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO removeShowroomById(@PathVariable("id") final Integer id) {
        return this.showroomService.removeShowroomById(id);
    }

}
