package com.example.showroommanagement.service;

import com.example.showroommanagement.entity.Department;
import com.example.showroommanagement.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    public Department createDepartment(final Department department) {
        return this.departmentRepository.save(department);
    }

    public Department retrieveDepartmentById(final Integer id) {
        return this.departmentRepository.findById(id).orElse(null);

    }

    public List<Department> retrieveDepartment() {
        return this.departmentRepository.findAll();
    }

    @Transactional
    public Department updateDepartmentById( final Department department, Integer id) {
        final Department existingDepartment = this.departmentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Data not found"));
        if (department.getId() != null) {
            existingDepartment.setId(department.getId());
        }
        if (department.getName() != null) {
            existingDepartment.setName(department.getName());
        }
        if (department. getVivoBranch() != null) {
            existingDepartment.setVivoBranch(department. getVivoBranch());
        }
        return this.departmentRepository.save(existingDepartment);
    }

    @Transactional
    public Map<String, String> removeDepartmentById(final Integer id) {
        this.departmentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("department not found with id: " + id));
        this.departmentRepository.deleteById(id);
        return (Map.of("message", "department deleted successfully."));
    }


}
