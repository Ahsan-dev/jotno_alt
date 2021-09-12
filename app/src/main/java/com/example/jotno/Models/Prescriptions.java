package com.example.jotno.Models;

public class Prescriptions {

    private String prescriptionNo, date, appointmentNo, doctorName, note;

    public Prescriptions(String prescriptionNo, String date, String appointmentNo, String doctorName, String note) {
        this.prescriptionNo = prescriptionNo;
        this.date = date;
        this.appointmentNo = appointmentNo;
        this.doctorName = doctorName;
        this.note = note;
    }


    public String getPrescriptionNo() {
        return prescriptionNo;
    }

    public void setPrescriptionNo(String prescriptionNo) {
        this.prescriptionNo = prescriptionNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAppointmentNo() {
        return appointmentNo;
    }

    public void setAppointmentNo(String appointmentNo) {
        this.appointmentNo = appointmentNo;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
