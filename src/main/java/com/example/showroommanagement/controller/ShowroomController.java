package com.example.showroommanagement.controller;

import com.example.showroommanagement.dto.ResponseDTO;
import com.example.showroommanagement.entity.Showroom;
import com.example.showroommanagement.service.ShowroomService;
import com.example.showroommanagement.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/showroom")
public class ShowroomController {
    private final ShowroomService showroomService;

    public ShowroomController(ShowroomService showroomService) {
        this.showroomService = showroomService;
    }

    @PostMapping("/create")
    public ResponseDTO createShowroom(@RequestBody final Showroom Showroom) {
        return new ResponseDTO(HttpStatus.OK.value(), this.showroomService.createShowroom(Showroom), Constant.CREATE);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO retrieveShowroomById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), this.showroomService.retrieveShowroomById(id), Constant.RETRIEVE);

    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieveShowroom() {
        return new ResponseDTO(HttpStatus.OK.value(), this.showroomService.retrieveShowroom(), Constant.RETRIEVE);
    }


    @PutMapping("/update/{id}")
    public ResponseDTO updateShowroom(@PathVariable final Integer id, @RequestBody final Showroom showroom) {
        return new ResponseDTO(HttpStatus.OK.value(), this.showroomService.updateShowroomById(showroom, id), Constant.UPDATE);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO removeShowroomById(@PathVariable("id") final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), this.showroomService.removeShowroomById(id), Constant.REMOVE);
    }


}
