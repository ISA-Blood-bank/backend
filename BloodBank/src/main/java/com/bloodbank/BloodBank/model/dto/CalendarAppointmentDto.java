package com.bloodbank.BloodBank.model.dto;

import java.time.LocalDateTime;

public class CalendarAppointmentDto {

    private int id;
    private LocalDateTime start;
    private LocalDateTime end;
    private String userName;
    private String userSurname;
    private float duration;

    public CalendarAppointmentDto() {
    }

    public CalendarAppointmentDto(int id, LocalDateTime start, LocalDateTime end, String userName, String userSurname, float duration) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.userName = userName;
        this.userSurname = userSurname;
        this.duration = duration;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
