package com.example.showroommanagement.controller;

import com.example.showroommanagement.dto.ResponseDTO;
import com.example.showroommanagement.entity.Manager;
import com.example.showroommanagement.service.ManagerService;
import com.example.showroommanagement.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/manager")
public class ManagerController {
    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping("/create")
    public ResponseDTO createManager(@RequestBody final Manager manager) {
        return new ResponseDTO(HttpStatus.OK.value(),this.managerService.createManager(manager), Constant.CREATE);
    }

    @GetMapping("/retrieve/{id}")
    public ResponseDTO retrieveManagerById(@PathVariable final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), this.managerService.retrieveManagerById(id),Constant.RETRIEVE );
    }

    @GetMapping("/retrieve")
    public ResponseDTO retrieveManager() {
        return new ResponseDTO(HttpStatus.OK.value(), this.managerService.retrieveManager(),Constant.RETRIEVE );
    }

    @PutMapping("/update/{id}")
    public ResponseDTO updateManagerById(@PathVariable final Integer id, @RequestBody final Manager manager) {
        return new ResponseDTO(HttpStatus.OK.value(),this.managerService.updateManagerById(manager,id),Constant.UPDATE);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseDTO removeManagerById(@PathVariable("id") final Integer id) {
        return new ResponseDTO(HttpStatus.OK.value(), this.managerService.removeManagerById(id), Constant.REMOVE);
    }


}
