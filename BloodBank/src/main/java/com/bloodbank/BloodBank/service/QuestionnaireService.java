package com.bloodbank.BloodBank.service;

import com.bloodbank.BloodBank.model.Questionnaire;
import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.model.enums.Gender;
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

    private boolean userExtractedATooth(Integer id){
        Questionnaire q = questionnaireRepository.findById(id).orElseGet(null);
        return q.isQuestion10();
    }

    private boolean userHasAllergies(Integer id){
        Questionnaire q = questionnaireRepository.findById(id).orElseGet(null);
        return q.isQuestion18();
    }

    private boolean userGotTattooed(Integer id){
        Questionnaire q = questionnaireRepository.findById(id).orElseGet(null);
        return q.isQuestion20();
    }

    private boolean userHasCold(Integer id){
        Questionnaire q = questionnaireRepository.findById(id).orElseGet(null);
        return q.isQuestion11();
    }

    private boolean userHasMenstruation(Integer id){
        Questionnaire q = questionnaireRepository.findById(id).orElseGet(null);
        RegistredUser ru = q.getRegistredUser();
        if(ru.getGender() == Gender.FEMALE){
            return  q.isQuestion25();
        }
        return false;
    }

    private boolean userTakesMedication(Integer id){
        Questionnaire q = questionnaireRepository.findById(id).orElseGet(null);

        return (q.isQuestion6() || q.isQuestion7() || q.isQuestion8());
    }
}
