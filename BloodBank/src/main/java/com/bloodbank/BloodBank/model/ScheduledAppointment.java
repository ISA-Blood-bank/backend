package com.bloodbank.BloodBank.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ScheduledAppointment")
public class ScheduledAppointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private Appointment appointment;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private RegistredUser user;
    private boolean passed;
    private boolean canceled;

    public ScheduledAppointment() {
    }

    public ScheduledAppointment(int id, Appointment appointment, RegistredUser user, boolean passed, boolean canceled) {
        this.id = id;
        this.appointment = appointment;
        this.user = user;
        this.passed = passed;
        this.canceled = canceled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public RegistredUser getUser() {
        return user;
    }

    public void setUser(RegistredUser user) {
        this.user = user;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}
