package com.bloodbank.BloodBank.model;

import java.io.Serializable;

public class ScheduledAppointment implements Serializable {
    private int id;
    private Appointment appointment;
    private RegistredUser user;
    private boolean passed;
    private boolean canceled;
}
