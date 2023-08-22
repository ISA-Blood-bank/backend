package com.bloodbank.BloodBank.controller;

import com.bloodbank.BloodBank.model.Questionnaire;
import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/questionnaire")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Questionnaire> save(@RequestBody Questionnaire questionnaire){
        Questionnaire newQuestionnaire = questionnaireService.save(questionnaire);
        return new ResponseEntity<>(newQuestionnaire, HttpStatus.OK);
    }

    @GetMapping("/extractedTooth/{id}")
    @PreAuthorize("hasRole('MEDSTAFF')")
    public ResponseEntity<Boolean> userHaveExtractedTooth(@PathVariable("id") Integer id){
        boolean extracted = questionnaireService.userExtractedATooth(id);
        return new ResponseEntity<>(extracted, HttpStatus.OK);
    }

    @GetMapping("/hasAllergies/{id}")
    @PreAuthorize("hasRole('MEDSTAFF')")
    public ResponseEntity<Boolean> userHasAllergies(@PathVariable("id") Integer id){
        boolean extracted = questionnaireService.userHasAllergies(id);
        return new ResponseEntity<>(extracted, HttpStatus.OK);
    }

    @GetMapping("/gotTattooed/{id}")
    @PreAuthorize("hasRole('MEDSTAFF')")
    public ResponseEntity<Boolean> userGotTattooed(@PathVariable("id") Integer id){
        boolean extracted = questionnaireService.userGotTattooed(id);
        return new ResponseEntity<>(extracted, HttpStatus.OK);
    }

    @GetMapping("/hasCold/{id}")
    @PreAuthorize("hasRole('MEDSTAFF')")
    public ResponseEntity<Boolean> userHasCold(@PathVariable("id") Integer id){
        boolean extracted = questionnaireService.userHasCold(id);
        return new ResponseEntity<>(extracted, HttpStatus.OK);
    }

    @GetMapping("/hasMenstruation/{id}")
    @PreAuthorize("hasRole('MEDSTAFF')")
    public ResponseEntity<Boolean> userHAsMenstruation(@PathVariable("id") Integer id){
        boolean extracted = questionnaireService.userHasMenstruation(id);
        return new ResponseEntity<>(extracted, HttpStatus.OK);
    }

    @GetMapping("/takesMedication/{id}")
    @PreAuthorize("hasRole('MEDSTAFF')")
    public ResponseEntity<Boolean> userTakesMedication(@PathVariable("id") Integer id){
        boolean extracted = questionnaireService.userTakesMedication(id);
        return new ResponseEntity<>(extracted, HttpStatus.OK);
    }
}
