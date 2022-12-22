package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findAllByBloodCenterId(int bloodCenterId);

    List<Appointment> findAllByMedicalStaffId(int medicalStaffId);
}
