package com.bloodbank.BloodBank.model.dto;

import com.bloodbank.BloodBank.model.Address;
import com.bloodbank.BloodBank.model.BloodCenter;
import com.bloodbank.BloodBank.model.enums.Gender;

public class MedicalStaffDto {
    private int id;
    private String name;
    private String surname;
    private String jmbg;
    private Gender gender;
    private String email;
    private String password1;
    private String password2;
    private Address address;
    private BloodCenter bloodCenter;

    public MedicalStaffDto(int id, String name, String surname, String jmbg, Gender gender, String email, String password1, String password2, Address address, BloodCenter bloodCenter) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.jmbg = jmbg;
        this.gender = gender;
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
        this.address = address;
        this.bloodCenter = bloodCenter;
    }

    @Override
    public String toString() {
        return "MedicalStaffDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", jmbg='" + jmbg + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", password1='" + password1 + '\'' +
                ", password2='" + password2 + '\'' +
                ", address=" + address +
                ", bloodCenter=" + bloodCenter +
                '}';
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

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BloodCenter getBloodCenter() {
        return bloodCenter;
    }

    public void setBloodCenter(BloodCenter bloodCenter) {
        this.bloodCenter = bloodCenter;
    }
}
