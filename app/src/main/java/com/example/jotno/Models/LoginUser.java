package com.example.jotno.Models;

import com.google.gson.annotations.SerializedName;

public class LoginUser {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    /**
     * No args constructor for use in serialization
     *
     */
    public LoginUser() {
    }

    public LoginUser(String mobile, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
