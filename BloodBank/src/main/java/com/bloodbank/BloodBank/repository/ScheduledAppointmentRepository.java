package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.Appointment;
import com.bloodbank.BloodBank.model.ScheduledAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduledAppointmentRepository extends JpaRepository<ScheduledAppointment, Integer> {
    @Query("select b from ScheduledAppointment b where b.user.id = ?1 and b.canceled = false")
    public List<ScheduledAppointment> findAllByUserId(int userId);

    @Query("select b from ScheduledAppointment b where b.user.id = ?1 and b.canceled = true")
    public List<ScheduledAppointment> findAllByUserIdCanceled(int userId);
}
