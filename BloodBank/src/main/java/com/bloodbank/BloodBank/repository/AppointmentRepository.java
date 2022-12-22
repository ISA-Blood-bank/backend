package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findAllByBloodCenterId(int bloodCenterId);

    List<Appointment> findAllByMedicalStaffId(int medicalStaffId);
=======
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
>>>>>>> 53e4afbd21cbde7b04ea248b6c659eb0cf7bf310
}
