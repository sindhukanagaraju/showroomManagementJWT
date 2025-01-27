package com.example.showroommanagement.service;

import com.example.showroommanagement.dto.ResponseDTO;
import com.example.showroommanagement.entity.Showroom;
import com.example.showroommanagement.exception.BadRequestServiceAlertException;
import com.example.showroommanagement.repository.ShowroomRepository;
import com.example.showroommanagement.util.Constant;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ShowroomService {

    private final ShowroomRepository showroomRepository;

    public ShowroomService(ShowroomRepository showroomRepository) {
        this.showroomRepository = showroomRepository;
    }


    @Transactional
    public ResponseDTO createShowroom(final Showroom showroom) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.CREATE);
        responseDTO.setStatusCode(HttpStatus.CREATED.value());
        responseDTO.setData(this.showroomRepository.save(showroom));
        return responseDTO;
    }

    @Transactional
    public ResponseDTO retrieveShowroomById(final Integer id) {
        if (this.showroomRepository.existsById(id)) {
            this.showroomRepository.findById(id);
            final ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(Constant.RETRIEVE);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(this.showroomRepository.findById(id));
            return responseDTO;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }

    public ResponseDTO retrieveShowroom() {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.showroomRepository.findAll());
        return responseDTO;
    }



    @Transactional
    public ResponseDTO updateShowroomById(final Showroom showroom, final Integer id) {
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
        if(showroom.getBrand()!=null){
            existingShowroom.setBrand(showroom.getBrand());
        }
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.UPDATE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.showroomRepository.save(existingShowroom));
        return responseDTO;
    }


    @Transactional
    public ResponseDTO removeShowroomById(final Integer id) {
        if (id == null) {
            throw new BadRequestServiceAlertException(Constant.DATA_NULL);
        }
        if (this.showroomRepository.existsById(id)) {
            this.showroomRepository.deleteById(id);
            final ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setMessage(Constant.DELETE);
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setData(Constant.REMOVE);
            return responseDTO;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }
}

