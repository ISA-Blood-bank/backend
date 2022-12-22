package com.bloodbank.BloodBank.model.dto;

import java.time.LocalDateTime;

public class AppointmentDto {
    private int id;
    private LocalDateTime start;
    private float duration;
    private boolean available;
    private int medicalStaffId;

    public AppointmentDto(int id, LocalDateTime start, float duration, boolean available, int medicalStaffId) {
        this.id = id;
        this.start = start;
        this.duration = duration;
        this.available = available;
        this.medicalStaffId = medicalStaffId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getMedicalStaffId() {
        return medicalStaffId;
    }

    public void setMedicalStaffId(int medicalStaffId) {
        this.medicalStaffId = medicalStaffId;
    }
}
