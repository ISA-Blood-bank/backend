package com.bloodbank.BloodBank.controller;

import com.bloodbank.BloodBank.model.BloodCenterGrade;
import com.bloodbank.BloodBank.model.dto.BloodCenterGradeDto;
import com.bloodbank.BloodBank.service.BloodCenterGradeService;
import com.bloodbank.BloodBank.service.BloodCenterSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/BloodCenterGrade")
public class BloodCenterGradeContoller {
    @Autowired
    private BloodCenterGradeService bloodCenterGradeService;

    @PostMapping(value = "/gradeBloodCenter")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Object> gradeBloodCenter(@RequestBody  BloodCenterGradeDto dto){
        if(bloodCenterGradeService.canGrade(dto.getBloodCenterId(), dto.getUserId())){
            BloodCenterGrade g = bloodCenterGradeService.findByBloodCenterIdAndUserId(dto.getBloodCenterId(), dto.getUserId());
            if (g != null){
                g.setGrade(dto.getGrade());
                g = bloodCenterGradeService.save(g);
                return new ResponseEntity<>(g, HttpStatus.OK);
            } else  {
                BloodCenterGrade grade = bloodCenterGradeService.gradeBloodCenter(dto);
                if(grade == null){
                    return new ResponseEntity<>("Uh oh, something went wrong with grading!",HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(grade, HttpStatus.CREATED);
            }
        } else {
            return new ResponseEntity<>("You haven't visited this blood center yet!",HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/canGrade/{id}/{bloodCenterId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Boolean> canGrade(@PathVariable("id") Integer id, @PathVariable("bloodCenterId") Integer bloodCenterId) {
        Boolean canGrade = bloodCenterGradeService.canGrade(bloodCenterId, id);

        return new ResponseEntity<>(canGrade, HttpStatus.OK);
    }
}
