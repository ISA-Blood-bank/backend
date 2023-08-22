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

    public boolean userExtractedATooth(Integer id){
        List<Questionnaire> q = questionnaireRepository.findTopByRegistredUser_IdOrderByDateDateDesc(id);
        Questionnaire q1 = q.get(0);
        return q1.isQuestion10();
    }

    public boolean userHasAllergies(Integer id){
        List<Questionnaire> q = questionnaireRepository.findTopByRegistredUser_IdOrderByDateDateDesc(id);
        Questionnaire q1 = q.get(0);
        return q1.isQuestion18();
    }

    public boolean userGotTattooed(Integer id){
        List<Questionnaire> q = questionnaireRepository.findTopByRegistredUser_IdOrderByDateDateDesc(id);
        Questionnaire q1 = q.get(0);
        return q1.isQuestion20();
    }

    public boolean userHasCold(Integer id){
        List<Questionnaire> q = questionnaireRepository.findTopByRegistredUser_IdOrderByDateDateDesc(id);
        Questionnaire q1 = q.get(0);
        return q1.isQuestion11();
    }

    public boolean userHasMenstruation(Integer id){
        List<Questionnaire> q = questionnaireRepository.findTopByRegistredUser_IdOrderByDateDateDesc(id);
        Questionnaire q1 = q.get(0);
        RegistredUser ru = q1.getRegistredUser();
        if(ru.getGender() == Gender.FEMALE){
            return  q1.isQuestion25();
        }
        return false;
    }

    public boolean userTakesMedication(Integer id){
        List<Questionnaire> q = questionnaireRepository.findTopByRegistredUser_IdOrderByDateDateDesc(id);
        Questionnaire q1 = q.get(0);

        return (q1.isQuestion6() || q1.isQuestion7() || q1.isQuestion8());
    }
    public Integer getQuestionnaireIdByUserId(Integer id){
        List<Questionnaire> q = questionnaireRepository.findTopByRegistredUser_IdOrderByDateDateDesc(id);
        Questionnaire q1 = q.get(0);

        return q1.getId();
    }

}
