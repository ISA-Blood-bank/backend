package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.BloodCenterGrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodCenterGradeRepository extends JpaRepository<BloodCenterGrade, Integer> {
    public BloodCenterGrade getBloodCenterGradeByBloodCenter_IdAndUser_Id(Integer bloodCenterId, Integer userId);
}