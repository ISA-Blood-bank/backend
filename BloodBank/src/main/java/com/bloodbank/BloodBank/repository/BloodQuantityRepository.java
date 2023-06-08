package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.BloodCenter;
import com.bloodbank.BloodBank.model.BloodQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloodQuantityRepository extends JpaRepository<BloodQuantity, Integer> {
    List<BloodQuantity> findAllByBloodCenterId(int bloodCenterId);
}
