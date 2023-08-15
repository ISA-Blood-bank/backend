package com.bloodbank.BloodBank.controller;

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

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ScheduledAppointment>> getAll(){
        List<ScheduledAppointment> appointments = scheduledAppointmentService.getAll();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ScheduledAppointment> getById(@PathVariable("id") Integer id){
        ScheduledAppointment appointment = scheduledAppointmentService.getById(id);
        if(appointment == null){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity<>(appointment, HttpStatus.OK);
    }
/*
    @GetMapping("/medStaff/{medicalStaffId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ScheduledAppointment>> getByMedicalStaffId(@PathVariable("medicalStaffId") Integer medicalStaffId){
        List<ScheduledAppointment> appointments = scheduledAppointmentService.getAllByMedicalStaffId(medicalStaffId);
        return  new ResponseEntity<>(appointments, HttpStatus.OK);
    }*/

}
