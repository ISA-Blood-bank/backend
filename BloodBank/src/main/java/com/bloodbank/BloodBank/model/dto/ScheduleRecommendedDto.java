package com.bloodbank.BloodBank.model.dto;

import java.time.LocalDateTime;

public class ScheduleRecommendedDto {
    private LocalDateTime start;
    private String time;
    private int bloodcenterid;

    public ScheduleRecommendedDto(LocalDateTime start, String time, int bloodcenterid) {
        this.start = start;
        this.time = time;
        this.bloodcenterid = bloodcenterid;
    }

    public ScheduleRecommendedDto() {
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getBloodcenterid() {
        return bloodcenterid;
    }

    public void setBloodcenterid(int bloodcenterid) {
        this.bloodcenterid = bloodcenterid;
    }
}
