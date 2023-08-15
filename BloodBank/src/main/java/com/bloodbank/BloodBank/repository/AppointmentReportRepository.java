package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.Appointment;
import com.bloodbank.BloodBank.model.AppointmentReport;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentReportRepository extends JpaRepository<AppointmentReport, Integer> {

    @Query("select b from AppointmentReport b where b.appointment.user.id = ?1")
    public List<AppointmentReport> findAllSorted(int userId, PageRequest pageable);
}
