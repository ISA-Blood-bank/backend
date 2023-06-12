package com.bloodbank.BloodBank.model.dto;

public class PasswordDto {
    public String email;
    public String password;

    public PasswordDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public PasswordDto() {
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
}
