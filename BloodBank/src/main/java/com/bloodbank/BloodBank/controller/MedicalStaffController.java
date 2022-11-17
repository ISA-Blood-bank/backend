package com.bloodbank.BloodBank.controller;

import com.bloodbank.BloodBank.model.MedicalStaff;
import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.service.MedicalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/medicalStaff")

public class MedicalStaffController {
    @Autowired
    private MedicalStaffService medicalStaffService;
    @PostMapping("/add")
    public ResponseEntity<MedicalStaff> addMedicalStaff(@RequestBody MedicalStaff user) {
        MedicalStaff newUser = medicalStaffService.addMedicalStaff(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

}
