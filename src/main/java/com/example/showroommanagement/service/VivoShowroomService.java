package com.example.showroommanagement.service;

import com.example.showroommanagement.entity.VivoShowroom;
import com.example.showroommanagement.repository.VivoShowroomRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class VivoShowroomService {

    private final VivoShowroomRepository vivoShowroomRepository;

    public VivoShowroomService(VivoShowroomRepository vivoShowroomRepository) {
        this.vivoShowroomRepository = vivoShowroomRepository;
    }

    public VivoShowroom createShowroom(final VivoShowroom VivoShowroom) {
        return this.vivoShowroomRepository.save(VivoShowroom);
    }

    public VivoShowroom retrieveShowroomById(final Integer id) {
        return this.vivoShowroomRepository.findById(id).orElse(null);

    }

    public List<VivoShowroom> retrieveShowroom() {
        return this.vivoShowroomRepository.findAll();
    }

    @Transactional
    public VivoShowroom updateShowroomById(final VivoShowroom VivoShowroom, Integer id) {
        final VivoShowroom existingVivoShowroom = this.vivoShowroomRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Data not found"));
        if (VivoShowroom.getName() != null) {
            existingVivoShowroom.setName(VivoShowroom.getName());
        }
        if (VivoShowroom.getId() != null) {
            existingVivoShowroom.setId(VivoShowroom.getId());
        }
        if (VivoShowroom.getAddress() != null){
            existingVivoShowroom.setAddress(VivoShowroom.getAddress());
        }
        if (VivoShowroom.getContactNumber() != null){
            existingVivoShowroom.setContactNumber(VivoShowroom.getContactNumber());
        }
        return this.vivoShowroomRepository.save(existingVivoShowroom);
    }

    @Transactional
    public Map<String, String> removeSchoolById(final Integer id) {
        this.vivoShowroomRepository.findById(id).orElseThrow(() -> new NoSuchElementException("showroom not found with id: " + id));
        this.vivoShowroomRepository.deleteById(id);
        return (Map.of("message", "showroom deleted successfully."));
    }


}
