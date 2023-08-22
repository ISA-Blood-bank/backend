package com.bloodbank.BloodBank.model.dto;

import java.time.LocalDateTime;

public class ScheduledDisplayDto {
    private int id;
    private String name;

    private String surname;
    private String email;
    private LocalDateTime start;
    private float duration;
    private boolean passed;
    private boolean canceled;

    private int userId;


    public ScheduledDisplayDto() {
    }

    public ScheduledDisplayDto(int id, String name, String surname, String email, LocalDateTime start, boolean passed, boolean canceled, float duration, int userId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.start = start;
        this.passed = passed;
        this.canceled = canceled;
        this.duration = duration;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
