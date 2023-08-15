package com.bloodbank.BloodBank.controller;

import com.bloodbank.BloodBank.model.Appointment;
import com.bloodbank.BloodBank.model.ScheduledAppointment;
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
}

