package com.example.showroommanagement.controller;

import com.example.showroommanagement.entity.Employee;
import com.example.showroommanagement.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public Employee createEmployee(@RequestBody final Employee employee) {
        return this.employeeService.createEmployee(employee);
    }

    @GetMapping("/retrieve/{id}")
    public Employee retrieveEmployeeById(@PathVariable final Integer id) {
        return this.employeeService.retrieveEmployeeById(id);
    }

    @GetMapping("/retrieve")
    public List<Employee> retrieveEmployee() {
        return this.employeeService.retrieveEmployee();
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployeeById(@PathVariable final Integer id, @RequestBody final Employee employee) {
        return this.employeeService.updateEmployeeById(employee, id);
    }

    @DeleteMapping("/remove/{id}")
    public Map<String, String> removeEmployeeById(@PathVariable("id") final Integer id) {
        return this.employeeService. removeEmployeeById(id);
    }


}
