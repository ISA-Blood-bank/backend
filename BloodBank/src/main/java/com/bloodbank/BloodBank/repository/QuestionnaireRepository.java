package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.Questionnaire;
import com.bloodbank.BloodBank.model.RegistredUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Integer> {
}
