package com.example.showroommanagement.service;

import com.example.showroommanagement.entity.Showroom;
import com.example.showroommanagement.exception.BadRequestServiceAlertException;
import com.example.showroommanagement.repository.ShowroomRepository;
import com.example.showroommanagement.util.Constant;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ShowroomService {

    private final ShowroomRepository showroomRepository;

    public ShowroomService(ShowroomRepository showroomRepository) {
        this.showroomRepository = showroomRepository;
    }

    @Transactional
    public Showroom createShowroom(final Showroom showroom) {
        return this.showroomRepository.save(showroom);

    }

    @Transactional
    public Showroom retrieveShowroomById(final Integer id) {
        return this.showroomRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
    }

    public List<Showroom> retrieveShowroom() {
        return this.showroomRepository.findAll();
    }

    @Transactional
    public Showroom updateShowroomById(final Showroom showroom, final Integer id) {
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
        if (showroom.getBrand() != null) {
            existingShowroom.setBrand(showroom.getBrand());
        }
        return this.showroomRepository.save(existingShowroom);
    }

    @Transactional
    public Showroom removeShowroomById(final Integer id) {
        if (id == null) {
            throw new BadRequestServiceAlertException(Constant.DATA_NULL);
        }
        Showroom showroom = this.showroomRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        this.showroomRepository.deleteById(id);
        return showroom;
    }
}

