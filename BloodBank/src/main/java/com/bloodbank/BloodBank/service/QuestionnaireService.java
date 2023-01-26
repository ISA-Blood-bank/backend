package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.Questionnaire;
import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.repository.QuestionnaireRepository;
import com.bloodbank.BloodBank.security.auth.TokenBasedAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.bloodbank.BloodBank.repository.RegisteredUserRepository;

import java.util.List;

@Service
public class QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private RegisteredUserRepository registredUserRepository;

    @Autowired
    private RegisteredUserService registeredUserService;

    public Questionnaire save(Questionnaire questionnaire){
        /*TokenBasedAuthentication authentication = (TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication();
        RegistredUser user = (RegistredUser) authentication.getPrincipal();*/
        RegistredUser user;
        try{
            TokenBasedAuthentication authentication = (TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication();
            user = (RegistredUser) authentication.getPrincipal();
        }catch (final Exception e){
            user = registeredUserService.findOne(1);
        }
        questionnaire.setRegistredUser(user);
        return questionnaireRepository.save(questionnaire);
    }
}
