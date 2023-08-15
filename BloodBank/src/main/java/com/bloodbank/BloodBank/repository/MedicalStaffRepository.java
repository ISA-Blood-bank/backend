package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.MedicalStaff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, Integer> {
    public List<MedicalStaff> findAllByBloodCenterId(Integer bloodCenterId);

}
