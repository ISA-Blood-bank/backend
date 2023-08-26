package com.bloodbank.BloodBank.controller;

import com.bloodbank.BloodBank.model.dto.BloodDto;
import com.bloodbank.BloodBank.service.BloodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/bloods")
public class BloodControler {

    @Autowired
    private BloodService bloodService;

    @GetMapping(value = "/getForBloodCenter/{bloodCenterId}")
    @PreAuthorize("hasRole('MEDSTAFF')")
    public ResponseEntity<List<BloodDto>> getBloodByBloodCenterId(@PathVariable("bloodCenterId") Integer bloddCenterId){
        List<BloodDto> dtoList = bloodService.getBloodByBloodCenterId(bloddCenterId);
        if(dtoList == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }


}
