package com.example.showroommanagement.service;

import com.example.showroommanagement.entity.Admin;
import com.example.showroommanagement.exception.BadRequestServiceAlertException;
import com.example.showroommanagement.repository.AdminRepository;
import com.example.showroommanagement.util.Constant;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(final AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Transactional
    public Admin createAdmin(final Admin admin) {
        return this.adminRepository.save(admin);
    }

    public Admin retrieveAdminById(final Integer id) {
        return this.adminRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
    }

    public List<Admin> retrieveAdmin() {
        return this.adminRepository.findAll();
    }

    @Transactional
    public Admin updateAdminById(final Admin admin, final Integer id) {
        final Admin existingAdmin = this.adminRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));

        if (admin.getName() != null) {
            existingAdmin.setName(admin.getName());
        }
        if (admin.getUser() != null) {
            existingAdmin.setUser(admin.getUser());
        }
        return this.adminRepository.save(existingAdmin);
    }

    public String removeAdminById(final Integer id) {
        if (this.adminRepository.existsById(id)) {
            this.adminRepository.deleteById(id);
            return Constant.DELETE;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }
}
