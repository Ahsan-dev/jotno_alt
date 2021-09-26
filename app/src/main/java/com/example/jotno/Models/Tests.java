package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tests {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("body")
    @Expose
    private TestsBody body;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TestsBody getBody() {
        return body;
    }

    public void setBody(TestsBody body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Tests{" +
                "status='" + status + '\'' +
                ", body=" + body +
                '}';
    }
}
