package com.bloodbank.BloodBank.model.dto;

import java.time.LocalDateTime;

public class AppointmentDto {
    private int id;
    private float duration;
    private LocalDateTime start;
    private int medicalStaffId;
    public AppointmentDto() {
    }

    public AppointmentDto(int id, float duration, LocalDateTime start, int medicalStaffId) {
        this.id = id;
        this.duration = duration;
        this.start = start;
        this.medicalStaffId = medicalStaffId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getMedicalStaffId() {
        return medicalStaffId;
    }

    public void setMedicalStaffId(int medicalStaffId) {
        this.medicalStaffId = medicalStaffId;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }
}
