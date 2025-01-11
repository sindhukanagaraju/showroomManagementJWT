package com.example.showroommanagement.service;

import com.example.showroommanagement.dto.SalesManDetailsDTO;
import com.example.showroommanagement.entity.SalesMan;
import com.example.showroommanagement.repository.SalesManRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class SalesManService {

    private final SalesManRepository salesManRepository;

    public SalesManService(SalesManRepository salesManRepository) {
        this.salesManRepository = salesManRepository;
    }

    public SalesMan createSalesMan(final SalesMan salesMan) {
        return this.salesManRepository.save(salesMan);
    }

    public SalesMan retrieveSalesManById(final Integer id) {
        return this.salesManRepository.findById(id).orElse(null);

    }

    public List<SalesMan> retrieveSalesMan() {
        return this.salesManRepository.findAll();
    }

    @Transactional
    public SalesMan updateSalesManById(final SalesMan salesMan, Integer id) {
        final SalesMan existingSalesMan = this.salesManRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Data not found"));
        if (salesMan.getId() != null) {
            existingSalesMan.setId(salesMan.getId());
        }
        if (salesMan.getName() != null) {
            existingSalesMan.setName(salesMan.getName());
        }
        if (salesMan.getSalary() != null) {
            existingSalesMan.setSalary(salesMan.getSalary());
        }
        if (salesMan.getAddress() != null) {
            existingSalesMan.setAddress(salesMan.getAddress());
        }
        if (salesMan.getShowroom() != null) {
            existingSalesMan.setShowroom(salesMan.getShowroom());
        }
        return this.salesManRepository.save(existingSalesMan);
    }

    @Transactional
    public Map<String, String> removeSalesManById(final Integer id) {
        this.salesManRepository.findById(id).orElseThrow(() -> new NoSuchElementException("salesman not found with id: " + id));
        this.salesManRepository.deleteById(id);
        return (Map.of("message", "salesman deleted successfully."));
    }


    public List<SalesManDetailsDTO>  retrieveSalesManDetails() {
        List<SalesMan> retrieveSalesMan = this.salesManRepository.findAll();
        List<SalesManDetailsDTO> salesManDetailsDTOS = new ArrayList<>();
        for (SalesMan salesMan : retrieveSalesMan) {
            SalesManDetailsDTO salesManDetailsDTO = new SalesManDetailsDTO();
            salesManDetailsDTO.setSalesManName(salesMan.getName());
            salesManDetailsDTO.setSalesManSalary(salesMan.getSalary());
            salesManDetailsDTO.setManagerName(salesMan.getShowroom().getManager().getName());
            salesManDetailsDTO.setShowroomName(salesMan.getShowroom().getName());
            salesManDetailsDTO.setShowroomAddress(salesMan.getShowroom().getAddress());
            salesManDetailsDTO.setManagerPhoneNumber(salesMan.getShowroom().getManager().getContactNumber());
            salesManDetailsDTO.setShowroomPhoneNumber(salesMan.getShowroom().getContactNumber());
            salesManDetailsDTOS.add(salesManDetailsDTO);
        }
        return salesManDetailsDTOS;
    }
}
