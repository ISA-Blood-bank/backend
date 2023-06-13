package com.bloodbank.BloodBank.controller;

import com.bloodbank.BloodBank.model.MedicalStaff;
import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.model.dto.MedicalStaffDto;
import com.bloodbank.BloodBank.model.dto.RegistredUserDto;
import com.bloodbank.BloodBank.service.MedicalStaffService;
import com.bloodbank.BloodBank.service.RegisteredUserService;
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
    @Autowired
    private RegisteredUserService registeredUserService;
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MedicalStaff> addMedicalStaff(@RequestBody MedicalStaffDto user) {
        RegistredUserDto registredUserDto= new RegistredUserDto(user.getId(),user.getName(),user.getSurname(),user.getJmbg(),
                user.getGender(), user.getEmail(), user.getPassword1(), user.getPassword2(), user.getAddress());
        RegistredUser registredUser = this.registeredUserService.addRegisteredUser(registredUserDto);
        MedicalStaff medicalStaff = new MedicalStaff();
        medicalStaff.setBloodCenter(user.getBloodCenter());
        medicalStaff.setRegisteredUser(registredUser);
        MedicalStaff newUser = medicalStaffService.addMedicalStaff(medicalStaff);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<MedicalStaff> getOneById(@PathVariable("id") Integer id){
        MedicalStaff ms = medicalStaffService.findById(id);
        return new ResponseEntity<>(ms, HttpStatus.OK);
    }
    @GetMapping("/findBloodCenterByUserId/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Integer> getBloodCenterIdByUserId(@PathVariable("id") Integer id){
        int ms = medicalStaffService.getBloodCenterIdByUserId(id);
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
