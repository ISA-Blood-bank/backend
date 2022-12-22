package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.Appointment;
import com.bloodbank.BloodBank.model.AppointmentReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentReportRepository extends JpaRepository<AppointmentReport, Integer> {
}
