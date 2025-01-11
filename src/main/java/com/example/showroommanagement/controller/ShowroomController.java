package com.example.showroommanagement.controller;

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
    public Showroom createShowroom(@RequestBody final Showroom Showroom) {
        return this.showroomService.createShowroom(Showroom);
    }

    @GetMapping("/retrieve/{id}")
    public Showroom retrieveShowroomById(@PathVariable final Integer id) {
        return this.showroomService.retrieveShowroomById(id);
    }

    @GetMapping("/retrieve")
    public List<Showroom> retrieveShowroom() {
        return this.showroomService.retrieveShowroom();
    }

    @PutMapping("/update/{id}")
    public Integer updateShowroom(@PathVariable final Integer id, @RequestBody final Showroom Showroom) {
        return this.showroomService.updateShowroomById(Showroom, id).getId();
    }

    @DeleteMapping("/remove/{id}")
    public Map<String, String> removeShowroomById(@PathVariable("id") final Integer id) {
        return this.showroomService.removeShowroomById(id);
    }

}
