package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.BloodCenter;
import com.bloodbank.BloodBank.model.RegistredUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodCenterRepository extends JpaRepository<BloodCenter, Integer> {
}
