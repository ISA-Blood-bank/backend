package com.bloodbank.BloodBank.exceptions;

public class OverlappingAppointmentException extends RuntimeException{

    public OverlappingAppointmentException(String message){
        super(message);
    }
}
