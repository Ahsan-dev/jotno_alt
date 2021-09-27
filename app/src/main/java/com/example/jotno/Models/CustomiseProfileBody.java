package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomiseProfileBody {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("patient")
    @Expose
    private UpdatedPatient patient;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UpdatedPatient getPatient() {
        return patient;
    }

    public void setPatient(UpdatedPatient patient) {
        this.patient = patient;
    }

}
