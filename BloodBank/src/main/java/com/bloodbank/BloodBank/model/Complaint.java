package com.bloodbank.BloodBank.model;

import com.bloodbank.BloodBank.model.enums.ComplaintType;

import java.io.Serializable;

public class Complaint implements Serializable {
    private int id;
    private RegistredUser user;
    private String content;
    private ComplaintType complaintType;
}
