package com.example.showroommanagement.controller;

import com.example.showroommanagement.entity.SalesDepartment;
import com.example.showroommanagement.service.SalesDepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/department")
public class SalesDepartmentController {
    private final SalesDepartmentService salesDepartmentService;

    public SalesDepartmentController(SalesDepartmentService salesDepartmentService) {
        this.salesDepartmentService = salesDepartmentService;
    }

    @PostMapping("/create")
    public SalesDepartment createDepartment(@RequestBody final SalesDepartment salesDepartment) {
        return this.salesDepartmentService.createDepartment(salesDepartment);
    }

    @GetMapping("/retrieve/{id}")
    public SalesDepartment retrieveDepartmentById(@PathVariable final Integer id) {
        return this.salesDepartmentService.retrieveDepartmentById(id);
    }

    @GetMapping("/retrieve")
    public List<SalesDepartment> retrieveDepartment() {
        return this.salesDepartmentService.retrieveDepartment();
    }

    @PutMapping("/update/{id}")
    public SalesDepartment updateDepartmentById(@PathVariable final Integer id, @RequestBody final SalesDepartment salesDepartment) {
        return this.salesDepartmentService.updateDepartmentById(salesDepartment, id);
    }

    @DeleteMapping("/remove/{id}")
    public Map<String, String> removeDepartmentById(@PathVariable("id") final Integer id) {
        return this.salesDepartmentService. removeDepartmentById(id);
    }


}
