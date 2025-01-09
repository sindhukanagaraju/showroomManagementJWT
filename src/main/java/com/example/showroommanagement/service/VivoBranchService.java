package com.example.showroommanagement.service;

import com.example.showroommanagement.entity.VivoBranch;
import com.example.showroommanagement.repository.VivoBranchRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class VivoBranchService {

    private final VivoBranchRepository vivoBranchRepository;

    public VivoBranchService(VivoBranchRepository vivoBranchRepository) {
        this.vivoBranchRepository = vivoBranchRepository;
    }

    public VivoBranch createVivoBranch(final VivoBranch vivoBranch) {
        return this.vivoBranchRepository.save(vivoBranch);
    }

    public VivoBranch retrieveVivoBranchById(final Integer id) {
        return this.vivoBranchRepository.findById(id).orElse(null);

    }

    public List<VivoBranch> retrieveVivoBranch() {
        return this.vivoBranchRepository.findAll();
    }

    @Transactional
    public VivoBranch updateVivoBranchById(final VivoBranch vivoBranch, Integer id) {
        final VivoBranch existingVivoBranch = this.vivoBranchRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Data not found"));
        if (vivoBranch.getId() != null) {
            existingVivoBranch.setId(vivoBranch.getId());
        }
        if (vivoBranch.getLocation() != null) {
            existingVivoBranch.setLocation(vivoBranch.getLocation());
        }
        if (vivoBranch.getVivoShowrooms() != null) {
            existingVivoBranch.setVivoShowrooms(vivoBranch.getVivoShowrooms());
        }
        return this.vivoBranchRepository.save(existingVivoBranch);
    }

    @Transactional
    public Map<String, String> removeVivoBranchById(final Integer id) {
        this.vivoBranchRepository.findById(id).orElseThrow(() -> new NoSuchElementException("branch not found with id: " + id));
        this.vivoBranchRepository.deleteById(id);
        return (Map.of("message", "Branch deleted successfully."));
    }

}
