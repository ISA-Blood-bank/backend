package com.bloodbank.BloodBank.model;

import com.bloodbank.BloodBank.model.enums.Category;
import com.bloodbank.BloodBank.model.enums.Gender;

import javax.persistence.*;

@Entity
public class RegistredUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    @Column(unique = true, nullable = false)
    private String jmbg;
    private Gender gender;
    private String email;
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;
    private String occupation;
    private String jobOrSchoolInfo;

    private float points;

    private Category category;

    private int penalties;

    public RegistredUser() {}

    public RegistredUser(int id, String name, String surname, String jmbg, Gender gender, String email, String password, Address address, String occupation, String jobOrSchoolInfo, float points, Category category, int penalties) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.jmbg = jmbg;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.address = address;
        this.occupation = occupation;
        this.jobOrSchoolInfo = jobOrSchoolInfo;
        this.points = points;
        this.category = category;
        this.penalties = penalties;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "RegistredUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", jmbg='" + jmbg + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address=" + address +
                ", occupation='" + occupation + '\'' +
                ", jobOrSchoolInfo='" + jobOrSchoolInfo + '\'' +
                ", points=" + points +
                ", category=" + category +
                ", penalties=" + penalties +
                '}';
    }
}
