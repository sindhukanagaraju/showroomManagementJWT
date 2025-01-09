package com.example.showroommanagement.service;

import com.example.showroommanagement.entity.VivoShowrooms;
import com.example.showroommanagement.repository.ShowroomRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class ShowroomService {

    private final ShowroomRepository showroomRepository;

    public ShowroomService(ShowroomRepository showroomRepository) {
        this.showroomRepository = showroomRepository;
    }

    public VivoShowrooms createShowroom(final VivoShowrooms VivoShowrooms) {
        return this.showroomRepository.save(VivoShowrooms);
    }

    public VivoShowrooms retrieveShowroomById(final Integer id) {
        return this.showroomRepository.findById(id).orElse(null);

    }

    public List<VivoShowrooms> retrieveShowroom() {
        return this.showroomRepository.findAll();
    }

    @Transactional
    public VivoShowrooms updateShowroomById(final VivoShowrooms VivoShowrooms, Integer id) {
        final VivoShowrooms existingVivoShowrooms = this.showroomRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Data not found"));
        if (VivoShowrooms.getName() != null) {
            existingVivoShowrooms.setName(VivoShowrooms.getName());
        }
        if (VivoShowrooms.getId() != null) {
            existingVivoShowrooms.setId(VivoShowrooms.getId());
        }
        if (VivoShowrooms.getAddress() != null){
            existingVivoShowrooms.setAddress(VivoShowrooms.getAddress());
        }
        if (VivoShowrooms.getContactNumber() != null){
            existingVivoShowrooms.setContactNumber(VivoShowrooms.getContactNumber());
        }
        return this.showroomRepository.save(existingVivoShowrooms);
    }

    @Transactional
    public Map<String, String> removeSchoolById(final Integer id) {
        this.showroomRepository.findById(id).orElseThrow(() -> new NoSuchElementException("showroom not found with id: " + id));
        this.showroomRepository.deleteById(id);
        return (Map.of("message", "showroom deleted successfully."));
    }


}
