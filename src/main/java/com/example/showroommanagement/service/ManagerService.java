package com.example.showroommanagement.service;

import com.example.showroommanagement.entity.Manager;
import com.example.showroommanagement.repository.ManagerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;

    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public Manager createManager(final Manager Manager) {
        return this.managerRepository.save(Manager);
    }

    public Manager retrieveManagerById(final Integer id) {
        return this.managerRepository.findById(id).orElse(null);

    }

    public List<Manager> retrieveManager() {
        return this.managerRepository.findAll();
    }

    @Transactional
    public Manager updateManagerById(final Manager manager, Integer id) {
        final Manager existingManager = this.managerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Data not found"));
        if (manager.getName() != null) {
            existingManager.setName(manager.getName());
        }
        if (manager.getId() != null) {
            existingManager.setId(manager.getId());
        }
        if (manager.getAddress() != null) {
            existingManager.setAddress(manager.getAddress());
        }
        if (manager.getContactNumber() != null) {
            existingManager.setContactNumber(manager.getContactNumber());
        }
        if (manager.getPassword() != null) {
            existingManager.setPassword(manager.getPassword());
        }
        return this.managerRepository.save(existingManager);
    }

    @Transactional
    public Map<String, String> removeManagerById(final Integer id) {
        this.managerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Manager not found with id: " + id));
        this.managerRepository.deleteById(id);
        return (Map.of("message", "Manager deleted successfully."));
    }
}

