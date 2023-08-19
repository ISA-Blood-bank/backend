package com.bloodbank.BloodBank.model.dto;

import com.bloodbank.BloodBank.model.enums.BloodType;

import java.time.LocalTime;

public class AdditionalInfoDto {
    private int id;
    private int questionaireId;
    private BloodType bloodType;
    private int medicalStaffId;
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

    private ScheduledAppointmentDto scheduledAppointmentDto;

    public AdditionalInfoDto(int id, int questionaireId, BloodType bloodType, int medicalStaffId, boolean bakarSulfat, boolean normalLevel, boolean highLevel, String hemoglobinometar, String value, boolean lungs, boolean heart, String TA, String TT, String TV, String bagType, String reasonForRejection, String reasonForAbort, LocalTime startTime, LocalTime endTime, boolean accepted, ScheduledAppointmentDto scheduledAppointmentDto) {
        this.id = id;
        this.questionaireId = questionaireId;
        this.bloodType = bloodType;
        this.medicalStaffId = medicalStaffId;
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
        this.scheduledAppointmentDto = scheduledAppointmentDto;
    }

    public AdditionalInfoDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionaireId() {
        return questionaireId;
    }

    public void setQuestionaireId(int questionaireId) {
        this.questionaireId = questionaireId;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public int getMedicalStaffId() {
        return medicalStaffId;
    }

    public void setMedicalStaffId(int medicalStaffId) {
        this.medicalStaffId = medicalStaffId;
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public ScheduledAppointmentDto getScheduledAppointmentDto() {
        return scheduledAppointmentDto;
    }

    public void setScheduledAppointmentDto(ScheduledAppointmentDto scheduledAppointmentDto) {
        this.scheduledAppointmentDto = scheduledAppointmentDto;
    }
}
