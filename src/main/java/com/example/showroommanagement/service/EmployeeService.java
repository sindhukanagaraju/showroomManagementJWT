package com.example.showroommanagement.service;

import com.example.showroommanagement.entity.Employee;
import com.example.showroommanagement.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public Employee createEmployee(final Employee employee) {
        return this.employeeRepository .save(employee);
    }

    public Employee retrieveEmployeeById(final Integer id) {
        return this.employeeRepository .findById(id).orElse(null);

    }

    public List<Employee> retrieveEmployee() {
        return this.employeeRepository .findAll();
    }

    @Transactional
    public Employee updateEmployeeById(final Employee employee, Integer id) {
        final Employee existingEmployee = this.employeeRepository .findById(id).orElseThrow(() -> new NoSuchElementException("Data not found"));
        if (employee.getId() != null) {
            existingEmployee.setId(employee.getId());
        }
        if (employee.getName() != null) {
            existingEmployee.setName(employee.getName());
        }
        if (employee. getSalary() != null) {
            existingEmployee.setSalary(employee. getSalary());
        }
        if (employee. getAddress() != null) {
            existingEmployee.setAddress(employee. getAddress());
        }
        if (employee.getSalesDepartment() != null) {
            existingEmployee.setSalesDepartment(employee.getSalesDepartment());
        }
        return this.employeeRepository .save(existingEmployee);
    }

    @Transactional
    public Map<String, String> removeEmployeeById(final Integer id) {
        this.employeeRepository .findById(id).orElseThrow(() -> new NoSuchElementException("employee not found with id: " + id));
        this.employeeRepository .deleteById(id);
        return (Map.of("message", "employee deleted successfully."));
    }

}
