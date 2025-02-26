package com.example.showroommanagement.service;

import com.example.showroommanagement.entity.Brand;
import com.example.showroommanagement.exception.BadRequestServiceAlertException;
import com.example.showroommanagement.repository.BrandRepository;
import com.example.showroommanagement.util.Constant;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(final BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Transactional
    public Brand createBrand(final Brand brand) {
        return this.brandRepository.save(brand);
    }

    public Brand retrieveBrandById(final Integer id) {
        return this.brandRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
    }

    public List<Brand> retrieveBrand() {
        return this.brandRepository.findAll();
    }

    @Transactional
    public Brand updateBrandById(final Brand brand, final Integer id) {
        final Brand existingBrand = this.brandRepository.findById(id).orElseThrow(() -> new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST));
        if (brand.getId() != null) {
            existingBrand.setId(brand.getId());
        }
        if (brand.getBrand() != null) {
            existingBrand.setBrand(brand.getBrand());
        }
        if (brand.getShowroom() != null) {
            existingBrand.setShowroom(brand.getShowroom());
        }
        return this.brandRepository.save(existingBrand);
    }

    public String removeBrandById(final Integer id) {
        if (this.brandRepository.existsById(id)) {
            this.brandRepository.deleteById(id);
            return Constant.DELETE;
        } else {
            throw new BadRequestServiceAlertException(Constant.ID_DOES_NOT_EXIST);
        }
    }
}
