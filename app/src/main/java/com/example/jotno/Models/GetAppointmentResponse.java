package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAppointmentResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("body")
    @Expose
    private Body body;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

}
