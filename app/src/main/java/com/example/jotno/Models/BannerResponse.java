package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BannerResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("body")
    @Expose
    private BannerBody body;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BannerBody getBody() {
        return body;
    }

    public void setBody(BannerBody body) {
        this.body = body;
    }

}
