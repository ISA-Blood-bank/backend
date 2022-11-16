package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.BloodCenter;
import com.bloodbank.BloodBank.model.MedicalStaff;
import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.model.enums.Category;
import com.bloodbank.BloodBank.repository.MedicalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalStaffService {
    @Autowired
    private MedicalStaffRepository medialStaffRepository;

    public MedicalStaff findOne(Integer id){
        return medialStaffRepository.findById(id).orElseGet(null);
    }

}
