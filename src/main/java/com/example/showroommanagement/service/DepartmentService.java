package com.example.showroommanagement.service;

import com.example.showroommanagement.entity.Department;
import com.example.showroommanagement.exception.BadRequestServiceAlertException;
import com.example.showroommanagement.repository.DepartmentRepository;
import com.example.showroommanagement.util.Constant;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(final DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public Department createDepartment(final Department Department) {
        return this.departmentRepository.save(Department);
    }

    public Department retrieveDepartmentById(final Integer id) {
        return this.departmentRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
    }

    public List<Department> retrieveDepartment() {
        return this.departmentRepository.findAll();
    }

    @Transactional
    public Department updateDepartmentById(final Department department, final Integer id) {
        final Department existingDepartment = this.departmentRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        if (department.getName() != null) {
            existingDepartment.setName(department.getName());
        }
        if (department.getId() != null) {
            existingDepartment.setId(department.getId());
        }

        if (department.getShowroom() != null) {
            existingDepartment.setShowroom(department.getShowroom());
        }
        return this.departmentRepository.save(existingDepartment);
    }

    public Department removeDepartmentById(final Integer id) {
        final Department branch = this.departmentRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        this.departmentRepository.deleteById(id);
        return branch;
    }
}

