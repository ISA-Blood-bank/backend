package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.Questionnaire;
import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bloodbank.BloodBank.repository.RegisteredUserRepository;

import java.util.List;

@Service
public class QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private RegisteredUserRepository registredUserRepository;

    public Questionnaire save(Questionnaire questionnaire){
        List<RegistredUser> all = registredUserRepository.findAll();
        questionnaire.setRegistredUser(all.get(0));
        return questionnaireRepository.save(questionnaire);
    }
}
