package com.bloodbank.BloodBank.model.dto;

import com.bloodbank.BloodBank.model.enums.Category;
import com.bloodbank.BloodBank.model.enums.Gender;

public class RegistredUserViewDto {
    private int id;
    private String name;
    private String surname;
    private String jmbg;
    private Gender gender;
    private String email;

    private String occupation;
    private String jobOrSchoolInfo;

    private float points;

    private Category category;

    private int penalties;

    private float weight;

    private String phone;

    public RegistredUserViewDto() {
    }

    public RegistredUserViewDto(int id, String name, String surname, String jmbg, Gender gender, String email, String occupation, String jobOrSchoolInfo, float points, Category category, int penalties, float weight, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.jmbg = jmbg;
        this.gender = gender;
        this.email = email;
        this.occupation = occupation;
        this.jobOrSchoolInfo = jobOrSchoolInfo;
        this.points = points;
        this.category = category;
        this.penalties = penalties;
        this.weight = weight;
        this.phone = phone;
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

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getJobOrSchoolInfo() {
        return jobOrSchoolInfo;
    }

    public void setJobOrSchoolInfo(String jobOrSchoolInfo) {
        this.jobOrSchoolInfo = jobOrSchoolInfo;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
