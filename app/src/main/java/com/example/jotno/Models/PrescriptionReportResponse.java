package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrescriptionReportResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("body")
    @Expose
    private ReportBody body;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ReportBody getBody() {
        return body;
    }

    public void setBody(ReportBody body) {
        this.body = body;
    }


}
