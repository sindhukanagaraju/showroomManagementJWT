package com.example.showroommanagement.controller;

import com.example.showroommanagement.entity.Manager;
import com.example.showroommanagement.service.ManagerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/manager")
public class ManagerController {
    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping("/create")
    public Manager createManager(@RequestBody final Manager manager) {
        return this.managerService.createManager(manager);
    }

    @GetMapping("/retrieve/{id}")
    public Manager retrieveManagerById(@PathVariable final Integer id) {
        return this.managerService.retrieveManagerById(id);
    }

    @GetMapping("/retrieve")
    public List<Manager> retrieveManager() {
        return this.managerService.retrieveManager();
    }

    @PutMapping("/update/{id}")
    public Manager updateManagerById(@PathVariable final Integer id, @RequestBody final Manager manager) {
        return this.managerService.updateManagerById(manager, id);
    }

    @DeleteMapping("/remove/{id}")
    public Map<String, String> removeManagerById(@PathVariable("id") final Integer id) {
        return this.managerService.removeManagerById(id);
    }


}
