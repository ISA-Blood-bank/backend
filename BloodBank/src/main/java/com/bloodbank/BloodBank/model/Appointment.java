package com.bloodbank.BloodBank.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "Appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime start;
    private float duration;
    private boolean available;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "blood_center_id")
    private BloodCenter bloodCenter;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medical_staff_id")
    private RegistredUser medicalStaff;

    public Appointment() {
    }

    public Appointment(int id, LocalDateTime start, float duration, boolean available, BloodCenter bloodCenter, RegistredUser medicalStaff) {
        this.id = id;
        this.start = start;
        this.duration = duration;
        this.available = available;
        this.bloodCenter = bloodCenter;
        this.medicalStaff = medicalStaff;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public float getDuration() {
        return duration;
    }

    public boolean isAvailable() {
        return available;
    }

    public BloodCenter getBloodCenter() {
        return bloodCenter;
    }

    public RegistredUser getMedicalStaff() {
        return medicalStaff;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setBloodCenter(BloodCenter bloodCenter) {
        this.bloodCenter = bloodCenter;
    }

    public void setMedicalStaff(RegistredUser medicalStaff) {
        this.medicalStaff = medicalStaff;
    }
}
