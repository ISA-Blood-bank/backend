package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.BloodCenter;
import com.bloodbank.BloodBank.model.RegistredUser;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegisteredUserRepository extends JpaRepository<RegistredUser, Integer> {
    @Query("select r from RegistredUser r where upper(r.name) = upper(?1) and upper(r.surname) = upper(?2) ")
    public List<RegistredUser> search(String inputName, String inputSurname);
}
