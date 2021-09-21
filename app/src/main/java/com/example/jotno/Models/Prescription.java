
package com.example.jotno.Models;


import com.example.jotno.Models.Patient;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Prescription {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("prescription_no")
    @Expose
    private String prescriptionNo;
    @SerializedName("appoinment_no")
    @Expose
    private String appoinmentNo;
    @SerializedName("advice")
    @Expose
    private String advice;
    @SerializedName("doctor")
    @Expose
    private Doctor doctor;
    @SerializedName("patient")
    @Expose
    private Patient patient;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrescriptionNo() {
        return prescriptionNo;
    }

    public void setPrescriptionNo(String prescriptionNo) {
        this.prescriptionNo = prescriptionNo;
    }

    public String getAppoinmentNo() {
        return appoinmentNo;
    }

    public void setAppoinmentNo(String appoinmentNo) {
        this.appoinmentNo = appoinmentNo;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

}
