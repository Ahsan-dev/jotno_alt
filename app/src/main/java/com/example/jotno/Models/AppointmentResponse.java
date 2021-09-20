
package com.example.jotno.Models;

import com.example.jotno.Models.AppointmentBody;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AppointmentResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("body")
    @Expose
    private AppointmentBody body;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AppointmentBody getBody() {
        return body;
    }

    public void setBody(AppointmentBody body) {
        this.body = body;
    }

}
