package com.example.showroommanagement.service;

import com.example.showroommanagement.dto.ResponseDTO;
import com.example.showroommanagement.entity.Manager;
import com.example.showroommanagement.exception.BadRequestServiceAlertException;
import com.example.showroommanagement.repository.ManagerRepository;
import com.example.showroommanagement.util.Constant;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;

    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public ResponseDTO createManager(final Manager manager) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setMessage(Constant.CREATE);
        responseDTO.setData(this.managerRepository.save(manager));
        return responseDTO;
    }

    public ResponseDTO retrieveManagerById(final Integer id) {
        if (this.managerRepository.existsById(id)) {
            this.managerRepository.findById(id);
            final ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setStatusCode(HttpStatus.OK.value());
            responseDTO.setMessage(Constant.RETRIEVE);
            responseDTO.setData(this.managerRepository.findById(id));
            return responseDTO;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }

    public ResponseDTO retrieveManager() {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.RETRIEVE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.managerRepository.findAll());
        return responseDTO;
    }


    @Transactional
    public ResponseDTO updateManagerById(final Manager manager, Integer id) {
        final Manager existingManager = this.managerRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
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
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(Constant.UPDATE);
        responseDTO.setStatusCode(HttpStatus.OK.value());
        responseDTO.setData(this.managerRepository.save(existingManager));
        return responseDTO;
    }

    @Transactional
    public ResponseDTO removeManagerById(final Integer id) {
        if (id == null) {
            throw new BadRequestServiceAlertException(Constant.DATA_NULL);
        }
        if (this.managerRepository.existsById(id)) {
            this.managerRepository.deleteById(id);
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

