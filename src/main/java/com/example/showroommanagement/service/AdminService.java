package com.example.showroommanagement.service;

import com.example.showroommanagement.entity.Admin;
import com.example.showroommanagement.repository.AdminRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin createAdmin(final Admin Admin) {
        return this.adminRepository.save(Admin);
    }

    public Admin retrieveAdminById(final Integer id) {
        return this.adminRepository.findById(id).orElse(null);

    }

    public List<Admin> retrieveAdmin() {
        return this.adminRepository.findAll();
    }

    @Transactional
    public Admin updateAdminById(final Admin admin, Integer id) {
        final Admin existingAdmin = this.adminRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Data not found"));
        if (admin.getName() != null) {
            existingAdmin.setName(admin.getName());
        }
        if (admin.getId() != null) {
            existingAdmin.setId(admin.getId());
        }
        if (admin.getAddress() != null) {
            existingAdmin.setAddress(admin.getAddress());
        }
        if (admin.getContactNumber() != null) {
            existingAdmin.setContactNumber(admin.getContactNumber());
        }
        if (admin.getPassword() != null) {
            existingAdmin.setPassword(admin.getPassword());
        }
        return this.adminRepository.save(existingAdmin);
    }

    @Transactional
    public Map<String, String> removeAdminById(final Integer id) {
        this.adminRepository.findById(id).orElseThrow(() -> new NoSuchElementException("admin not found with id: " + id));
        this.adminRepository.deleteById(id);
        return (Map.of("message", "admin deleted successfully."));
    }
}

