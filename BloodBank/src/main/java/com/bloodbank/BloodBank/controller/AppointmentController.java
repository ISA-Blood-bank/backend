package com.bloodbank.BloodBank.controller;

import com.bloodbank.BloodBank.model.Appointment;
import com.bloodbank.BloodBank.model.BloodCenter;
import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.model.dto.AppointmentDto;
import com.bloodbank.BloodBank.model.dto.CalendarFreeAppointmentDto;
import com.bloodbank.BloodBank.model.dto.RecommendDto;
import com.bloodbank.BloodBank.model.dto.ScheduleRecommendedDto;
import com.bloodbank.BloodBank.service.AppointmentService;
import com.bloodbank.BloodBank.service.BloodCenterSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;


    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Appointment>> getAll(){
        List<Appointment> appointments = appointmentService.findAll();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
    @GetMapping("/allCalendarFree/{id}")
    public ResponseEntity<List<CalendarFreeAppointmentDto>> getAllFreeForCalendar(@PathVariable("id") Integer id){
        List<CalendarFreeAppointmentDto> appointments = appointmentService.findByBloodCenterId(id);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
    @PutMapping("/schedule/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Appointment> scheduleAppointment(@PathVariable("id") Integer id) throws MessagingException {
        Appointment appointment = appointmentService.getById(id);
        Appointment scheduled = appointmentService.scheduleAppointment(appointment);

        if(scheduled == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(scheduled, HttpStatus.OK);
    }

    @GetMapping(value = "/sorted/{page}/{size}/{sortList}/{order}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Appointment>> findAllSortedAndAvailable(@PathVariable Integer page,
                                                                      @PathVariable Integer size,
                                                                      @PathVariable String sortList,
                                                                      @PathVariable String order

    ) {
        List<Appointment> appointments = appointmentService.findAllSortedAndAvailable(page, size, sortList, order);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MEDSTAFF')")
    public ResponseEntity<Appointment> createNewAppointment(@RequestBody AppointmentDto appointment) {
        LocalDateTime time = appointment.getStart().plusHours(2);
        appointment.setStart(time);
        Appointment appointmentNew = appointmentService.createNewAvailableAppointment(appointment);
        return new ResponseEntity<>(appointmentNew, HttpStatus.CREATED);
    }
    @PostMapping("/getavailable")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Appointment>> getAvailableAppointments(@RequestBody RecommendDto recommendDto){
        LocalDateTime time = recommendDto.getStart().plusHours(2);
        recommendDto.setStart(time);
        List<Appointment> available = appointmentService.getAvailableAppointments(recommendDto);
        return new ResponseEntity<>(available, HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/schedule-recommended-appointment")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> scheduleRecommendedAppointment(@RequestBody ScheduleRecommendedDto dto) throws MessagingException {
        //TODO:generisanje QR koda
        RecommendDto newDto = new RecommendDto();
        newDto.setTime(dto.getTime());
        newDto.setStart(dto.getStart().plusHours(2));
        Appointment appointment = appointmentService.scheduleRecommendedAppointment(newDto, dto.getBloodcenterid());
        if (appointment != null) {
            return ResponseEntity.ok().body("{\"message\": \"Appointment scheduled successfully.\"}");
        } else {
            return ResponseEntity.ok().body("{\"message\": \"Could not schedule the appointment.\"}");
        }
    }
}
