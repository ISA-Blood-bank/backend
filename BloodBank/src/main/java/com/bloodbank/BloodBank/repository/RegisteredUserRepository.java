package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.RegistredUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredUserRepository extends JpaRepository<RegistredUser, Integer> {
}
