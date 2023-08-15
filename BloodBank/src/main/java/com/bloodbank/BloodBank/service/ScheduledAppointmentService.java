package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.Appointment;
import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.model.ScheduledAppointment;
import com.bloodbank.BloodBank.repository.AppointmentRepository;
import com.bloodbank.BloodBank.repository.ScheduledAppointmentRepository;
import com.bloodbank.BloodBank.security.auth.TokenBasedAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduledAppointmentService {
    @Autowired
    private ScheduledAppointmentRepository scheduledAppointmentRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;
    public List<ScheduledAppointment> findAllByUserId(){
        TokenBasedAuthentication authentication = (TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication();
        RegistredUser user = (RegistredUser) authentication.getPrincipal();
        int userId = user.getId();
        List<ScheduledAppointment> all = scheduledAppointmentRepository.findAllByUserId(userId);
        List<ScheduledAppointment> future = new ArrayList<>();
        for(ScheduledAppointment sa : all){
            if(sa.getAppointment().getStart().isAfter(LocalDateTime.now())){
                future.add(sa);
            }
        }
        return future;
    }

    public ScheduledAppointment cancelAppointment(int id){
        ScheduledAppointment canceled = scheduledAppointmentRepository.findById(id).orElseGet(null);
        if(canceled == null){
            return canceled;
        }

        if(IsBefore24Hours(canceled.getAppointment().getStart())){
            canceled.setCanceled(true);
            Appointment availableAgain = appointmentRepository.findById(canceled.getAppointment().getId()).orElseGet(null);
            availableAgain.setAvailable(true);
            appointmentRepository.save(availableAgain);
            return scheduledAppointmentRepository.save(canceled);
        }
        return null;
    }

    private boolean IsBefore24Hours(LocalDateTime appointmentStart){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime plus24Hours = now.plusHours(24);
        if(plus24Hours.isBefore(appointmentStart)){
            return true;
        }
        return false;
    }
}