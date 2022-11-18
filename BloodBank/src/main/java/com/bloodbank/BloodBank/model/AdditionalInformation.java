package com.bloodbank.BloodBank.model;

import com.bloodbank.BloodBank.model.enums.BloodType;

import java.time.LocalTime;

public class AdditionalInformation {
    private  Questionnaire questionnaire;
    private BloodType bloodType;
    private MedicalStaff medicalStaff;
    private boolean bakarSulfat;
    private boolean normalLevel;
    private boolean highLevel;
    private String  hemoglobinometar;
    private String  value;
    private boolean  lungs;
    private boolean  heart;
    private String  TA;
    private String  TT;
    private String  TV;
    private String  bagType;
    private String  reasonForRejection;
    private String  reasonForAbort;
    private LocalTime startTime;
    private LocalTime  endTime;
    private boolean  accepted;



}
