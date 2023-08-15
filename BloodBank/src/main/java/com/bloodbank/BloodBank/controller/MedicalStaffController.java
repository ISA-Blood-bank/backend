package com.bloodbank.BloodBank.controller;

import com.bloodbank.BloodBank.model.MedicalStaff;
import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.service.MedicalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/find/{id}")
    public ResponseEntity<MedicalStaff> getOneById(@PathVariable("id") Integer id){
        MedicalStaff ms = medicalStaffService.findById(id);
        return new ResponseEntity<>(ms, HttpStatus.OK);
    }

    @GetMapping("/all-medical-staff/{bloodCenterId}")
    public ResponseEntity<List<MedicalStaff>> findAll(@PathVariable("bloodCenterId") Integer bloodCenterId){
        List<MedicalStaff> meds = medicalStaffService.findAllByBloodCenterId(bloodCenterId);
        return  new ResponseEntity<>(meds, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<MedicalStaff> updateMedicalStaff(@RequestBody MedicalStaff ms){
        MedicalStaff newMedicalStaff = medicalStaffService.updateMedicalStaff(ms);
        return new ResponseEntity<>(newMedicalStaff, HttpStatus.OK);
    }

}
