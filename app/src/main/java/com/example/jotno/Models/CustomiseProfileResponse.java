package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomiseProfileResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("body")
    @Expose
    private CustomiseProfileBody body;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CustomiseProfileBody getBody() {
        return body;
    }

    public void setBody(CustomiseProfileBody body) {
        this.body = body;
    }

}
