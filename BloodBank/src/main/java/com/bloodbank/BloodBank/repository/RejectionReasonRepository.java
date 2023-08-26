package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.RejectionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RejectionReasonRepository extends JpaRepository<RejectionInfo, Integer> {
}
