package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.Appointment;
import com.bloodbank.BloodBank.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    public Appointment add(Appointment a){
        return appointmentRepository.save(a);
    }

    public Appointment findOne(Integer id){
        return appointmentRepository.findById(id).orElseGet(null);
    }

    public List<Appointment> findByBloodCenterId(int bloodCenterId){
        return appointmentRepository.findAllByBloodCenterId(bloodCenterId);
    }
}
