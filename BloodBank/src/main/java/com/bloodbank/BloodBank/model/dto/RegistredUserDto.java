package com.bloodbank.BloodBank.model.dto;

import com.bloodbank.BloodBank.model.Address;
import com.bloodbank.BloodBank.model.RegistredUser;
import com.bloodbank.BloodBank.model.enums.Category;
import com.bloodbank.BloodBank.model.enums.Gender;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class RegistredUserDto {
    private int id;
    private String name;
    private String surname;
    private String jmbg;
    private Gender gender;
    private String email;
    private String password1;
    private String password2;
    private Address address;
    private String occupation;
    private String jobOrSchoolInfo;

    private float points;

    private Category category;

    private int penalties;

    private float weight;

    private String phone;

    public RegistredUserDto(RegistredUser registredUser) {
        this.id = registredUser.getId();

    }

    public RegistredUserDto(){

    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getJmbg() {
        return jmbg;
    }

    public Gender getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword1() {
        return password1;
    }

    public String getPassword2() {
        return password2;
    }

    public Address getAddress() {
        return address;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getJobOrSchoolInfo() {
        return jobOrSchoolInfo;
    }

    public float getPoints() {
        return points;
    }

    public Category getCategory() {
        return category;
    }

    public int getPenalties() {
        return penalties;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setJobOrSchoolInfo(String jobOrSchoolInfo) {
        this.jobOrSchoolInfo = jobOrSchoolInfo;
    }

    public void setPoints(float points) {
        this.points = points;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
