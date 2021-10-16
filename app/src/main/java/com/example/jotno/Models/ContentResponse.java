package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("body")
    @Expose
    private ContentBody body;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ContentBody getBody() {
        return body;
    }

    public void setBody(ContentBody body) {
        this.body = body;
    }

}
