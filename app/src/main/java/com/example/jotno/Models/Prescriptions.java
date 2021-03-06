package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prescriptions {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("body")
    @Expose
    private PrescriptionList body;

    public PrescriptionList getBody() {
        return body;
    }

    public void setBody(PrescriptionList body) {
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
