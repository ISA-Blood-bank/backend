package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.Blood;
import com.bloodbank.BloodBank.model.enums.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BloodRepository extends JpaRepository<Blood, Integer> {
    @Query("select b from Blood b where b.bloodCenter.id = ?1 and b.type = ?2")
    public Blood getBloodByBloodCenterIdAndBloodType(int bloodCenterId, BloodType type);
}
