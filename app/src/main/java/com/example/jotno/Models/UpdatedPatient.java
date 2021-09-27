package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdatedPatient {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("patient_id ")
    @Expose
    private String patientId;
    @SerializedName("name ")
    @Expose
    private String name;
    @SerializedName("email ")
    @Expose
    private String email;
    @SerializedName("phone ")
    @Expose
    private String phone;
    @SerializedName("date_of_birth ")
    @Expose
    private String dateOfBirth;
    @SerializedName("blood_group ")
    @Expose
    private String bloodGroup;
    @SerializedName("gender ")
    @Expose
    private String gender;
    @SerializedName("address ")
    @Expose
    private String address;
    @SerializedName("city ")
    @Expose
    private String city;
    @SerializedName("district ")
    @Expose
    private String district;
    @SerializedName("image ")
    @Expose
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
