package com.bloodbank.BloodBank.controller;

import com.bloodbank.BloodBank.model.Appointment;
import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Appointment>> getAll(){
        List<Appointment> appointments = appointmentService.findAll();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PutMapping("/schedule/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Appointment> scheduleAppointment(@PathVariable("id") Integer id) {
        Appointment scheduled = appointmentService.scheduleAppointment(id);
        if(scheduled == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(scheduled, HttpStatus.OK);
    }
}
