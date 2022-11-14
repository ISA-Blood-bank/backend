package com.bloodbank.BloodBank.model;

import com.bloodbank.BloodBank.model.enums.Category;
import com.bloodbank.BloodBank.model.enums.Gender;

import javax.persistence.Entity;

public class RegistredUser extends User{
    private String occupation;
    private String jobOrSchoolInfo;

    private float points;

    private Category category;

    private int penalties;

    public RegistredUser() {}

    public RegistredUser(int id, String name, String surname, String jmbg, Gender gender, String email, String password, Address address, String occupation, String jobOrSchoolInfo, float points, Category category) {
        super(id, name, surname, jmbg, gender, email, password, address);
        this.occupation = occupation;
        this.jobOrSchoolInfo = jobOrSchoolInfo;
        this.points = points;
        this.category = category;
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

    @Override
    public String toString() {
        return "RegistredUser{" +
                "occupation='" + occupation + '\'' +
                ", jobOrSchoolInfo='" + jobOrSchoolInfo + '\'' +
                '}';
    }
}
