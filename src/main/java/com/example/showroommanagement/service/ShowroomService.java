package com.example.showroommanagement.service;

import com.example.showroommanagement.entity.Showroom;
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

    public Showroom createShowroom(final Showroom Showroom) {
        return this.showroomRepository.save(Showroom);
    }

    public Showroom retrieveShowroomById(final Integer id) {
        return this.showroomRepository.findById(id).orElse(null);

    }

    public List<Showroom> retrieveShowroom() {
        return this.showroomRepository.findAll();
    }

    @Transactional
    public Showroom updateShowroomById(final Showroom showroom, Integer id) {
        final Showroom existingShowroom = this.showroomRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Data not found"));
        if (showroom.getName() != null) {
            existingShowroom.setName(showroom.getName());
        }
        if (showroom.getId() != null) {
            existingShowroom.setId(showroom.getId());
        }
        if (showroom.getAddress() != null) {
            existingShowroom.setAddress(showroom.getAddress());
        }
        if (showroom.getContactNumber() != null) {
            existingShowroom.setContactNumber(showroom.getContactNumber());
        }
        if (showroom.getManager() != null) {
            existingShowroom.setManager(showroom.getManager());
        }
        return this.showroomRepository.save(existingShowroom);
    }

    @Transactional
    public Map<String, String> removeShowroomById(final Integer id) {
        this.showroomRepository.findById(id).orElseThrow(() -> new NoSuchElementException("showroom not found with id: " + id));
        this.showroomRepository.deleteById(id);
        return (Map.of("message", "showroom deleted successfully."));
    }


}
