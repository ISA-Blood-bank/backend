package com.bloodbank.BloodBank.controller;

import com.bloodbank.BloodBank.model.*;
import com.bloodbank.BloodBank.model.dto.AdditionalInfoDto;
import com.bloodbank.BloodBank.model.dto.ScheduledAppointmentDto;
import com.bloodbank.BloodBank.service.ScheduledAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/scheduledAppointments")
public class ScheduledAppointmentController {

    @Autowired
    private ScheduledAppointmentService scheduledAppointmentService;

    @GetMapping("/getAllForLoggedUser")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ScheduledAppointment>> findAllForLoggedUser(){
        List<ScheduledAppointment> list = scheduledAppointmentService.findAllByUserId();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/cancel/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ScheduledAppointment> cancelAppointment(@PathVariable("id") Integer id) {
        ScheduledAppointment canceled = scheduledAppointmentService.cancelAppointment(id);
        if(canceled == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(canceled, HttpStatus.OK);
    }

    @PutMapping("/patientNotCome")
    @PreAuthorize("hasRole('MEDSTAFF')")
    public ResponseEntity<ScheduledAppointment> patientNotCome(@RequestBody ScheduledAppointmentDto dto){
        ScheduledAppointment appointment = scheduledAppointmentService.patientNotCome(dto);
        if(appointment == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @PostMapping("/appointmentFinish")
    @PreAuthorize("hasRole('MEDSTAFF')")
    public ResponseEntity<ScheduledAppointment> finishAppointment(@RequestBody AdditionalInfoDto dto){
        AdditionalInformation additionalInformation = scheduledAppointmentService.saveAdditionalInfo(dto);
        if(additionalInformation == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Visits visits = scheduledAppointmentService.addVisitsForUser(dto.getScheduledAppointmentDto());
        if(visits == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Blood blood = scheduledAppointmentService.addBloodToCentre(dto);
        if(blood == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ScheduledAppointment scheduledAppointment = scheduledAppointmentService.findById(dto.getScheduledAppointmentDto().getId());
        if(scheduledAppointment == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(scheduledAppointment, HttpStatus.OK);
    }
}

