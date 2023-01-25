package com.bloodbank.BloodBank.model.dto;

import java.time.LocalDateTime;

public class RecommendDto {
    private LocalDateTime start;
    private String time;

    public RecommendDto() {
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
}

