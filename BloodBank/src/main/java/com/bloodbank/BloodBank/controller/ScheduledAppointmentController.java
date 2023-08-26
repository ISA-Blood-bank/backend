package com.bloodbank.BloodBank.controller;

import com.bloodbank.BloodBank.model.*;
import com.bloodbank.BloodBank.model.dto.AdditionalInfoDto;
import com.bloodbank.BloodBank.model.dto.RejectionReasonDto;
import com.bloodbank.BloodBank.model.dto.ScheduledAppointmentDto;
import com.bloodbank.BloodBank.model.dto.ScheduledDisplayDto;
import com.bloodbank.BloodBank.repository.ScheduledAppointmentRepository;
import com.bloodbank.BloodBank.service.BloodService;
import com.bloodbank.BloodBank.service.QuestionnaireService;
import com.bloodbank.BloodBank.service.RejectionReasonService;
import com.bloodbank.BloodBank.service.ScheduledAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "api/scheduledAppointments")
public class ScheduledAppointmentController {

    @Autowired
    private ScheduledAppointmentService scheduledAppointmentService;

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private BloodService bloodService;

    @Autowired
    ScheduledAppointmentRepository scheduledAppointmentRepository;

    @Autowired
    private RejectionReasonService rejectionReasonService;

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
        appointment.setPassed(true);
        appointment.setCanceled(true);

        appointment = scheduledAppointmentRepository.save(appointment);
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

        ScheduledAppointment scheduledAppointment = scheduledAppointmentService.findById(dto.getScheduledAppointmentId());

        Visits visits = scheduledAppointmentService.addVisitsForUser(scheduledAppointment);
        if(visits == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Blood blood = bloodService.addBloodToCentre(dto);
        if(blood == null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ScheduledAppointment scheduledAppointment1 = scheduledAppointmentService.setPassed(scheduledAppointment);

        return new ResponseEntity<>(scheduledAppointment1, HttpStatus.OK);
    }

    @GetMapping(value = "/allScheduledAppointments/{id}")
    @PreAuthorize("hasRole('MEDSTAFF')")
    public ResponseEntity<List<ScheduledDisplayDto>> getAllScheduledAppointments(@PathVariable("id") Integer id){
        List<ScheduledDisplayDto> scheduledAppointments = scheduledAppointmentService.getByBloodCenterId(id);
        if(scheduledAppointments ==null){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(scheduledAppointments, HttpStatus.OK);
    }

    @GetMapping(value = "/getScheduledAppointment/{id}")
    @PreAuthorize("hasRole('MEDSTAFF')")
    public ResponseEntity<ScheduledDisplayDto> getById(@PathVariable("id") Integer id){
        ScheduledAppointment s = scheduledAppointmentService.findById(id);

        if(s == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ScheduledDisplayDto dto = new ScheduledDisplayDto(
                s.getId(),
                s.getUser().getName(),
                s.getUser().getSurname(),
                s.getUser().getEmail(),
                s.getAppointment().getStart(),
                s.getAppointment().getDuration(),
                s.isCanceled(),
                s.isPassed(),
                s.getUser().getId(),
                s.getAppointment().getId()
        );


        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/getQuestionaireId/{id}")
    @PreAuthorize("hasRole('MEDSTAFF')")
    public ResponseEntity<Integer> getQuestionnaireIdByUserId(@PathVariable("id") Integer id){
        Integer qId = questionnaireService.getQuestionnaireIdByUserId(id);
        if(qId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(qId, HttpStatus.OK);
    }

    @PostMapping(value = "/rejectPatient")
    @PreAuthorize("hasRole('MEDSTAFF')")
    public ResponseEntity<RejectionInfo> rejectPatient(@RequestBody RejectionReasonDto dto){
        ScheduledAppointment sc = scheduledAppointmentService.findById(dto.getScheduledAppointmentId());
        if(sc == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        sc.setPassed(true);
        scheduledAppointmentRepository.save(sc);

        RejectionInfo rejectionInfo = rejectionReasonService.save(dto);
        if (rejectionInfo == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(rejectionInfo, HttpStatus.CREATED);

    }
}

