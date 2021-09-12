package com.example.jotno.Models;

public class Appointments {

    private String appointmentNo, appointmentStatus, appointmentDate;

    public Appointments(String appointmentNo, String appointmentStatus, String appointmentDate) {
        this.appointmentNo = appointmentNo;
        this.appointmentStatus = appointmentStatus;
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentNo() {
        return appointmentNo;
    }

    public void setAppointmentNo(String appointmentNo) {
        this.appointmentNo = appointmentNo;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
