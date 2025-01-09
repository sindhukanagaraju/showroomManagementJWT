package com.example.showroommanagement.controller;

import com.example.showroommanagement.entity.Department;
import com.example.showroommanagement.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/create")
    public Department createDepartment(@RequestBody final Department department) {
        return this.departmentService.createDepartment(department);
    }

    @GetMapping("/retrieve/{id}")
    public Department retrieveDepartmentById(@PathVariable final Integer id) {
        return this.departmentService.retrieveDepartmentById(id);
    }

    @GetMapping("/retrieve")
    public List<Department> retrieveDepartment() {
        return this.departmentService.retrieveDepartment();
    }

    @PutMapping("/update/{id}")
    public Department updateDepartmentById(@PathVariable final Integer id, @RequestBody final Department department) {
        return this.departmentService.updateDepartmentById(department, id);
    }

    @DeleteMapping("/remove/{id}")
    public Map<String, String> removeDepartmentById(@PathVariable("id") final Integer id) {
        return this.departmentService. removeDepartmentById(id);
    }


}
