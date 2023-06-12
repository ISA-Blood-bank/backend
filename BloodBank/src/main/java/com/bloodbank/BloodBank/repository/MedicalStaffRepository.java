package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.Appointment;
import com.bloodbank.BloodBank.model.MedicalStaff;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, Integer> {
    public List<MedicalStaff> findAllByBloodCenterId(Integer bloodCenterId);

    @Query("select b from MedicalStaff b where b.registeredUser.id = :id")
    public MedicalStaff findByRegUserId(@Param("id") int id);

    @Query("select b from MedicalStaff b where b.bloodCenter.id = :id")
    public List<MedicalStaff> findByBloodCenterId(@Param("id") int id);


}
