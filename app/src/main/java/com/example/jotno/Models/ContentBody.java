package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentBody {

    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("about_title")
    @Expose
    private String aboutTitle;
    @SerializedName("about_description")
    @Expose
    private String aboutDescription;
    @SerializedName("about_image")
    @Expose
    private String aboutImage;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("facebook_url")
    @Expose
    private String facebookUrl;
    @SerializedName("twitter_url")
    @Expose
    private String twitterUrl;
    @SerializedName("linkedin_url")
    @Expose
    private String linkedinUrl;
    @SerializedName("satisfied_patient")
    @Expose
    private String satisfiedPatient;
    @SerializedName("patient_per_year")
    @Expose
    private String patientPerYear;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAboutTitle() {
        return aboutTitle;
    }

    public void setAboutTitle(String aboutTitle) {
        this.aboutTitle = aboutTitle;
    }

    public String getAboutDescription() {
        return aboutDescription;
    }

    public void setAboutDescription(String aboutDescription) {
        this.aboutDescription = aboutDescription;
    }

    public String getAboutImage() {
        return aboutImage;
    }

    public void setAboutImage(String aboutImage) {
        this.aboutImage = aboutImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    public void setLinkedinUrl(String linkedinUrl) {
        this.linkedinUrl = linkedinUrl;
    }

    public String getSatisfiedPatient() {
        return satisfiedPatient;
    }

    public void setSatisfiedPatient(String satisfiedPatient) {
        this.satisfiedPatient = satisfiedPatient;
    }

    public String getPatientPerYear() {
        return patientPerYear;
    }

    public void setPatientPerYear(String patientPerYear) {
        this.patientPerYear = patientPerYear;
    }

}
