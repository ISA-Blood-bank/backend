package com.bloodbank.BloodBank.model.dto;

public class ScheduledAppointmentDto {
    private int id;
    private int appointmentId;
    private int registredUesrId;
    private boolean passed;
    private boolean canceled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getRegistredUesrId() {
        return registredUesrId;
    }

    public void setRegistredUesrId(int registredUesrId) {
        this.registredUesrId = registredUesrId;
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

    public ScheduledAppointmentDto() {
    }

    public ScheduledAppointmentDto(int id, int appointmentId, int registredUesrId, boolean passed, boolean canceled) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.registredUesrId = registredUesrId;
        this.passed = passed;
        this.canceled = canceled;
    }
}
