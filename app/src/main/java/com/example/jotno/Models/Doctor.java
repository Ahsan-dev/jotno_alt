
package com.example.jotno.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Doctor {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("doctor_id")
    @Expose
    private String doctorId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("chamber")
    @Expose
    private String chamber;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("in")
    @Expose
    private String in;
    @SerializedName("out")
    @Expose
    private String out;
    @SerializedName("days")
    @Expose
    private Days days;

    public Doctor(Integer id, String doctorId, String name, String designation, String chamber, String location, String phone, String email, String in, String out, Days days) {
        this.id = id;
        this.doctorId = doctorId;
        this.name = name;
        this.designation = designation;
        this.chamber = chamber;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.in = in;
        this.out = out;
        this.days = days;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public Days getDays() {
        return days;
    }

    public void setDays(Days days) {
        this.days = days;
    }

}
