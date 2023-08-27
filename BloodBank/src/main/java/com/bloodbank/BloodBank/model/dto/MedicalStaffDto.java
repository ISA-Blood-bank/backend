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
    private int addressId;
    private int bloodCenterId;

    public MedicalStaffDto() {
    }

    public MedicalStaffDto(int id, String name, String surname, String jmbg, Gender gender, String email, int addressId, int bloodCenterId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.jmbg = jmbg;
        this.gender = gender;
        this.email = email;
        this.addressId = addressId;
        this.bloodCenterId = bloodCenterId;
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getBloodCenterId() {
        return bloodCenterId;
    }

    public void setBloodCenterId(int bloodCenterId) {
        this.bloodCenterId = bloodCenterId;
    }
}
