package com.bloodbank.BloodBank.model.dto;

import java.time.LocalDateTime;

public class CalendarFreeAppointmentDto {

    private int id;
    private LocalDateTime start;
    private LocalDateTime end;
    private String name;
    private String surname;
    private String medStaffName;
    private String medStaffSurname;
    private float duration;

    public String getMedStaffName() {
        return medStaffName;
    }

    public void setMedStaffName(String medStaffName) {
        this.medStaffName = medStaffName;
    }

    public String getMedStaffSurname() {
        return medStaffSurname;
    }

    public void setMedStaffSurname(String medStaffSurname) {
        this.medStaffSurname = medStaffSurname;
    }

    public CalendarFreeAppointmentDto(int id, LocalDateTime start, LocalDateTime end, String name, String surname, String medStaffName, String medStaffSurname, float duration) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.name = name;
        this.surname = surname;
        this.medStaffName = medStaffName;
        this.medStaffSurname = medStaffSurname;
        this.duration = duration;
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


    public CalendarFreeAppointmentDto() {
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

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
}
