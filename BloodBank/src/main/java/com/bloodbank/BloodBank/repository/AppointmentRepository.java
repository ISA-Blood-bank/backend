package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.Appointment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("select b from Appointment b where b.available = true")
    public List<Appointment> findAllSortedAndAvailable(PageRequest pageable);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select b from Appointment  b where b.bloodCenter.id = ?1")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    public List<Appointment> findByBloodCenter_Id(Integer bloodCenterId);
}
