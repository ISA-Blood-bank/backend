package com.bloodbank.BloodBank.exceptions;

public class AdminAlreadyExists extends RuntimeException{

    public AdminAlreadyExists(String message){
        super(message);
    }
}
