package com.bloodbank.BloodBank.model.dto;

import java.time.LocalDateTime;

public class CalendarFreeAppointmentDto {

    private int id;
    private LocalDateTime start;
    private LocalDateTime end;
    private float duration;

    public CalendarFreeAppointmentDto(int id, LocalDateTime start, LocalDateTime end, float duration) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.duration = duration;
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
