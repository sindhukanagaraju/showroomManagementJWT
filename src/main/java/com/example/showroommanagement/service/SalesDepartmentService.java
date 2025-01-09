package com.example.showroommanagement.service;

import com.example.showroommanagement.entity.SalesDepartment;
import com.example.showroommanagement.repository.SalesDepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class SalesDepartmentService {

    private final SalesDepartmentRepository salesDepartmentRepository;

    public SalesDepartmentService(SalesDepartmentRepository salesDepartmentRepository) {
        this.salesDepartmentRepository = salesDepartmentRepository;
    }
    public SalesDepartment createDepartment(final SalesDepartment salesDepartment) {
        return this.salesDepartmentRepository.save(salesDepartment);
    }

    public SalesDepartment retrieveDepartmentById(final Integer id) {
        return this.salesDepartmentRepository.findById(id).orElse(null);

    }

    public List<SalesDepartment> retrieveDepartment() {
        return this.salesDepartmentRepository.findAll();
    }

    @Transactional
    public SalesDepartment updateDepartmentById(final SalesDepartment salesDepartment, Integer id) {
        final SalesDepartment existingSalesDepartment = this.salesDepartmentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Data not found"));
        if (salesDepartment.getId() != null) {
            existingSalesDepartment.setId(salesDepartment.getId());
        }
        if (salesDepartment.getName() != null) {
            existingSalesDepartment.setName(salesDepartment.getName());
        }
        if (salesDepartment. getVivoShowroom() != null) {
            existingSalesDepartment.setVivoShowroom(salesDepartment.getVivoShowroom());
        }
        return this.salesDepartmentRepository.save(existingSalesDepartment);
    }

    @Transactional
    public Map<String, String> removeDepartmentById(final Integer id) {
        this.salesDepartmentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("department not found with id: " + id));
        this.salesDepartmentRepository.deleteById(id);
        return (Map.of("message", "department deleted successfully."));
    }


}
