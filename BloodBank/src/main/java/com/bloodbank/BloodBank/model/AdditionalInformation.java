package com.bloodbank.BloodBank.model;

import com.bloodbank.BloodBank.model.enums.BloodType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class AdditionalInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "questionaire_id", referencedColumnName = "id")
    private  Questionnaire questionnaire;
    private BloodType bloodType;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "medical_staff_id", referencedColumnName = "id")
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
    private LocalDateTime startTime;
    private LocalDateTime  endTime;
    private boolean  accepted;

    public AdditionalInformation() {
    }

    public AdditionalInformation(int id, Questionnaire questionnaire, BloodType bloodType, MedicalStaff medicalStaff, boolean bakarSulfat, boolean normalLevel, boolean highLevel, String hemoglobinometar, String value, boolean lungs, boolean heart, String TA, String TT, String TV, String bagType, String reasonForRejection, String reasonForAbort, LocalDateTime startTime, LocalDateTime endTime, boolean accepted) {
        this.id = id;
        this.questionnaire = questionnaire;
        this.bloodType = bloodType;
        this.medicalStaff = medicalStaff;
        this.bakarSulfat = bakarSulfat;
        this.normalLevel = normalLevel;
        this.highLevel = highLevel;
        this.hemoglobinometar = hemoglobinometar;
        this.value = value;
        this.lungs = lungs;
        this.heart = heart;
        this.TA = TA;
        this.TT = TT;
        this.TV = TV;
        this.bagType = bagType;
        this.reasonForRejection = reasonForRejection;
        this.reasonForAbort = reasonForAbort;
        this.startTime = startTime;
        this.endTime = endTime;
        this.accepted = accepted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public MedicalStaff getMedicalStaff() {
        return medicalStaff;
    }

    public void setMedicalStaff(MedicalStaff medicalStaff) {
        this.medicalStaff = medicalStaff;
    }

    public boolean isBakarSulfat() {
        return bakarSulfat;
    }

    public void setBakarSulfat(boolean bakarSulfat) {
        this.bakarSulfat = bakarSulfat;
    }

    public boolean isNormalLevel() {
        return normalLevel;
    }

    public void setNormalLevel(boolean normalLevel) {
        this.normalLevel = normalLevel;
    }

    public boolean isHighLevel() {
        return highLevel;
    }

    public void setHighLevel(boolean highLevel) {
        this.highLevel = highLevel;
    }

    public String getHemoglobinometar() {
        return hemoglobinometar;
    }

    public void setHemoglobinometar(String hemoglobinometar) {
        this.hemoglobinometar = hemoglobinometar;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isLungs() {
        return lungs;
    }

    public void setLungs(boolean lungs) {
        this.lungs = lungs;
    }

    public boolean isHeart() {
        return heart;
    }

    public void setHeart(boolean heart) {
        this.heart = heart;
    }

    public String getTA() {
        return TA;
    }

    public void setTA(String TA) {
        this.TA = TA;
    }

    public String getTT() {
        return TT;
    }

    public void setTT(String TT) {
        this.TT = TT;
    }

    public String getTV() {
        return TV;
    }

    public void setTV(String TV) {
        this.TV = TV;
    }

    public String getBagType() {
        return bagType;
    }

    public void setBagType(String bagType) {
        this.bagType = bagType;
    }

    public String getReasonForRejection() {
        return reasonForRejection;
    }

    public void setReasonForRejection(String reasonForRejection) {
        this.reasonForRejection = reasonForRejection;
    }

    public String getReasonForAbort() {
        return reasonForAbort;
    }

    public void setReasonForAbort(String reasonForAbort) {
        this.reasonForAbort = reasonForAbort;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
