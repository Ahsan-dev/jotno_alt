package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("success")
    @Expose
    private String success;

    /**
     * No args constructor for use in serialization
     *
     */
    public RegisterResponse() {
    }

    /**
     *
     * @param success
     */
    public RegisterResponse(String success) {
        super();
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "RegisterResponse{" +
                "success='" + success + '\'' +
                '}';
    }
}
