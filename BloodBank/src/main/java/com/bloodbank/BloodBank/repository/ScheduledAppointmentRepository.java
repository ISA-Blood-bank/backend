package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.ScheduledAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledAppointmentRepository extends JpaRepository<ScheduledAppointment, Integer> {
}
