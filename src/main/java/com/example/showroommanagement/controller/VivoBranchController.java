package com.example.showroommanagement.controller;

import com.example.showroommanagement.entity.VivoBranch;
import com.example.showroommanagement.service.VivoBranchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/branch")

public class VivoBranchController {

    private final VivoBranchService vivoBranchService;

    public VivoBranchController(VivoBranchService vivoBranchService) {
        this.vivoBranchService = vivoBranchService;
    }

    @PostMapping("/create")
    public VivoBranch createVivoBranch(@RequestBody final VivoBranch vivoBranch) {
        return this.vivoBranchService.createVivoBranch(vivoBranch);
    }

    @GetMapping("/retrieve/{id}")
    public VivoBranch  retrieveVivoBranchById(@PathVariable final Integer id) {
        return this.vivoBranchService.retrieveVivoBranchById(id);
    }

    @GetMapping("/retrieve")
    public List<VivoBranch > retrieveVivoBranch() {
        return this.vivoBranchService.retrieveVivoBranch();
    }

    @PutMapping("/update/{id}")
    public VivoBranch  updateVivoBranchById(@PathVariable final Integer id, @RequestBody final VivoBranch  vivoBranch) {
        return this.vivoBranchService.updateVivoBranchById(vivoBranch, id);
    }

    @DeleteMapping("/remove/{id}")
    public Map<String, String> removeVivoBranchById(@PathVariable("id") final Integer id) {
        return this.vivoBranchService. removeVivoBranchById(id);
    }

}
