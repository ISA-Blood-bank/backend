package com.bloodbank.BloodBank.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
@Entity
public class Appointment implements Serializable {
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
    private MedicalStaff medicalStaff;

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

    public BloodCenter getBloodCenter() {
        return bloodCenter;
    }

    public void setBloodCenter(BloodCenter bloodCenter) {
        this.bloodCenter = bloodCenter;
    }

    public MedicalStaff getMedicalStaff() {
        return medicalStaff;
    }

    public void setMedicalStaff(MedicalStaff medicalStaff) {
        this.medicalStaff = medicalStaff;
    }

    public Appointment(int id, LocalDateTime start, float duration, boolean available, BloodCenter bloodCenter, MedicalStaff medicalStaff) {
        this.id = id;
        this.start = start;
        this.duration = duration;
        this.available = available;
        this.bloodCenter = bloodCenter;
        this.medicalStaff = medicalStaff;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", start=" + start +
                ", duration=" + duration +
                ", available=" + available +
                ", bloodCenter=" + bloodCenter +
                ", medicalStaff=" + medicalStaff +
                '}';
    }

    public Appointment() {
    }
}


