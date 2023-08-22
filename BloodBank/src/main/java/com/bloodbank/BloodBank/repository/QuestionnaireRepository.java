package com.bloodbank.BloodBank.repository;

import com.bloodbank.BloodBank.model.Questionnaire;
import com.bloodbank.BloodBank.model.RegistredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Integer> {

   @Query(value = "select q from Questionnaire q where  q.registredUser.id=?1 order by q.date desc")
   List<Questionnaire> findTopByRegistredUser_IdOrderByDateDateDesc(Integer id);
}
