package com.example.showroommanagement.controller;

import com.example.showroommanagement.entity.Admin;
import com.example.showroommanagement.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/create")
    public Admin createAdmin(@RequestBody final Admin admin) {
        return this.adminService.createAdmin(admin);
    }

    @GetMapping("/retrieve/{id}")
    public Admin retrieveAdminById(@PathVariable final Integer id) {
        return this.adminService.retrieveAdminById(id);
    }

    @GetMapping("/retrieve")
    public List<Admin> retrieveAdmin() {
        return this.adminService.retrieveAdmin();
    }

    @PutMapping("/update/{id}")
    public Admin updateAdminById(@PathVariable final Integer id, @RequestBody final Admin admin) {
        return this.adminService.updateAdminById(admin, id);
    }

    @DeleteMapping("/remove/{id}")
    public Map<String, String> removeAdminById(@PathVariable("id") final Integer id) {
        return this.adminService.removeAdminById(id);
    }


}
