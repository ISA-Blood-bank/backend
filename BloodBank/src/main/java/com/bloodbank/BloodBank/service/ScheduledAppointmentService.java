package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.model.ScheduledAppointment;
import com.bloodbank.BloodBank.repository.RegisteredUserRepository;
import com.bloodbank.BloodBank.repository.ScheduledAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

@Service
public class ScheduledAppointmentService {
    @Autowired
    private ScheduledAppointmentRepository scheduledAppointmentRepository;

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    public ScheduledAppointmentService(ScheduledAppointmentRepository scheduledAppointmentRepository) {
        this.scheduledAppointmentRepository = scheduledAppointmentRepository;
    }

    public ScheduledAppointment getById(Integer id){
        return scheduledAppointmentRepository.findById(id).orElseGet(null);
    }

    public List<ScheduledAppointment> getAll(){
        return scheduledAppointmentRepository.findAll();
    }

    public List<ScheduledAppointment> getByUserId(Integer userId){
        return scheduledAppointmentRepository.findAllByUserId(userId);
    }

    public ScheduledAppointment updateScheduledAppointment(ScheduledAppointment appointment){
        return scheduledAppointmentRepository.save(appointment);
    }

    public void userDidntCome(ScheduledAppointment appointment){
        appointment.setPassed(false);
        RegistredUser user = appointment.getUser();
        user.setPenalties(user.getPenalties() + 1);
        registeredUserRepository.save(user);
    }
/*
    public List<ScheduledAppointment> getAllByMedicalStaffId(int medicalStaffId){
        return scheduledAppointmentRepository.findAllByMedicalStaffId(medicalStaffId);
    }*/


}
