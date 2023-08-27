package com.bloodbank.BloodBank.controller;

import com.bloodbank.BloodBank.model.MedicalStaff;
import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.model.dto.MedicalStaffDto;
import com.bloodbank.BloodBank.model.dto.RegistredUserViewDto;
import com.bloodbank.BloodBank.service.MedicalStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('MEDSTAFF')")
    public ResponseEntity<List<MedicalStaffDto>> findAll(@PathVariable("bloodCenterId") Integer bloodCenterId){
        List<MedicalStaffDto> meds = medicalStaffService.findAllByBloodCenterId(bloodCenterId);
        return  new ResponseEntity<>(meds, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<MedicalStaff> updateMedicalStaff(@RequestBody MedicalStaff ms){
        MedicalStaff newMedicalStaff = medicalStaffService.updateMedicalStaff(ms);
        return new ResponseEntity<>(newMedicalStaff, HttpStatus.OK);
    }

    @GetMapping(value = "/allVisited/{bloodCenterId}")
    @PreAuthorize("hasRole('MEDSTAFF')")
    public ResponseEntity<List<RegistredUserViewDto>> getUserWhoVisited(@PathVariable("bloodCenterId") Integer bloodCenterId){
        List<RegistredUserViewDto> users = medicalStaffService.getUserWhoVisitedBloodCenter(bloodCenterId);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/myProfile/{id}")
    @PreAuthorize("hasRole('MEDSTAFF')")
    public ResponseEntity<MedicalStaffDto> getMyProfile(@PathVariable("id") Integer id){
        MedicalStaff ms = medicalStaffService.findById(id);
        if(ms == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        MedicalStaffDto dto = new MedicalStaffDto(
                ms.getId(),
                ms.getName(),
                ms.getSurname(),
                ms.getJmbg(),
                ms.getGender(),
                ms.getEmail(),
                ms.getAddress().getId(),
                ms.getBloodCenter().getId()
        );

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
