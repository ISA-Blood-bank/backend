package com.bloodbank.BloodBank.controller;

import com.bloodbank.BloodBank.model.BloodCenter;
import com.bloodbank.BloodBank.model.MedicalStaff;
import com.bloodbank.BloodBank.model.dto.BloodCenterDto;
import com.bloodbank.BloodBank.service.BloodCenterSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/bloodCenters")
public class BloodCenterController {

    @Autowired
    private BloodCenterSevice bloodCenterSevice;

    public BloodCenterController(BloodCenterSevice bloodCenterSevice){
        this.bloodCenterSevice = bloodCenterSevice;
    }
    @GetMapping(value = "/all")
    public ResponseEntity<List<BloodCenter>> findAll(){

        List<BloodCenter> bloodCenters = bloodCenterSevice.findAll();

        List<BloodCenterDto> bloodCenterDtos = new ArrayList<>();
        for (BloodCenter bc : bloodCenters){
            bloodCenterDtos.add(new BloodCenterDto(bc));
        }

        return new ResponseEntity<>(bloodCenters, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<BloodCenter> addBloodCenter(@RequestBody BloodCenter bc) {
        BloodCenter bcnew = bloodCenterSevice.addBloodCenter(bc);
        return new ResponseEntity<>(bcnew, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<BloodCenter> findOne(@PathVariable("id") Integer id){
        BloodCenter bc = bloodCenterSevice.findOne(id);
        return  new ResponseEntity<>(bc, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<BloodCenter> update(@RequestBody BloodCenter bc){
        BloodCenter newBloodCenter = bloodCenterSevice.update(bc);
        return new ResponseEntity<BloodCenter>(newBloodCenter, HttpStatus.OK);
    }

}
