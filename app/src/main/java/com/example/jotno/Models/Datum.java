
package com.example.jotno.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Datum {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("appoinment_no")
    @Expose
    private String appoinmentNo;
    @SerializedName("appoinment_date")
    @Expose
    private String appoinmentDate;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("patient")
    @Expose
    private Patient patient;
    @SerializedName("doctor")
    @Expose
    private Doctor doctor;
    @SerializedName("prescription_id")
    @Expose
    private Integer prescriptionId;
    @SerializedName("initial_tests")
    @Expose
    private InitialTests initialTests;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppoinmentNo() {
        return appoinmentNo;
    }

    public void setAppoinmentNo(String appoinmentNo) {
        this.appoinmentNo = appoinmentNo;
    }

    public String getAppoinmentDate() {
        return appoinmentDate;
    }

    public void setAppoinmentDate(String appoinmentDate) {
        this.appoinmentDate = appoinmentDate;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Integer getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public InitialTests getInitialTests() {
        return initialTests;
    }

    public void setInitialTests(InitialTests initialTests) {
        this.initialTests = initialTests;
    }
}
