package com.bloodbank.BloodBank.model.dto;

public class BloodCenterGradeDto {
    private int id;
    private int userId;

    private int bloodCenterId;
    private float grade;

    public BloodCenterGradeDto() {
    }

    public BloodCenterGradeDto(int id, int userId, int bloodCenterId, float grade) {
        this.id = id;
        this.userId = userId;
        this.bloodCenterId = bloodCenterId;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBloodCenterId() {
        return bloodCenterId;
    }

    public void setBloodCenterId(int bloodCenterId) {
        this.bloodCenterId = bloodCenterId;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
