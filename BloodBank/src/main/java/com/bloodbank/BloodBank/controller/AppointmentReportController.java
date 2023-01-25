package com.bloodbank.BloodBank.controller;

import com.bloodbank.BloodBank.model.Appointment;
import com.bloodbank.BloodBank.model.AppointmentReport;
import com.bloodbank.BloodBank.service.AppointmentReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/appointmentReports")
public class AppointmentReportController {

    @Autowired
    private AppointmentReportService appointmentReportService;

    @GetMapping(value = "/sorted/{page}/{size}/{sortList}/{order}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<AppointmentReport>> findAllSortedAndAvailable(@PathVariable Integer page,
                                                                       @PathVariable Integer size,
                                                                       @PathVariable String sortList,
                                                                       @PathVariable String order

    ){
        List<AppointmentReport> appointmentReports = appointmentReportService.findAllSorted(page, size, sortList, order);
        return new ResponseEntity<>(appointmentReports, HttpStatus.OK);
    }

}
