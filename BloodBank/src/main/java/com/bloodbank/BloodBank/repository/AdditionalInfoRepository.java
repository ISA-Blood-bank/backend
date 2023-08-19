package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.AdditionalInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalInfoRepository extends JpaRepository<AdditionalInformation, Integer> {
}
