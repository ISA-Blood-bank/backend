package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.Appointment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("select b from Appointment b where b.available = true")
    public List<Appointment> findAllSortedAndAvailable(PageRequest pageable);

    @Query("select b from Appointment b where b.bloodCenter.id = :bc_id")
    public List<Appointment> findByBloodCenterId(@Param("bc_id") int bc_id);
}
