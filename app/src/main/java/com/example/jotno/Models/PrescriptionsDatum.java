package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrescriptionsDatum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("prescription_no")
    @Expose
    private String prescriptionNo;
    @SerializedName("appoinment_no")
    @Expose
    private String appoinmentNo;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("doctor_name")
    @Expose
    private String doctorName;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("charge")
    @Expose
    private String charge;
    @SerializedName("advice")
    @Expose
    private String advice;

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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }



    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

}
