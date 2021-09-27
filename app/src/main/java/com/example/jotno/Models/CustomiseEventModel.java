package com.example.jotno.Models;

public class CustomiseEventModel {

    private String eventTag;
    private String message;
    public UpdatedPatient patient;

    public CustomiseEventModel(String eventTag, String message, UpdatedPatient patient) {
        this.eventTag = eventTag;
        this.message = message;
        this.patient = patient;
    }

    public String getEventTag() {
        return eventTag;
    }

    public void setEventTag(String eventTag) {
        this.eventTag = eventTag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UpdatedPatient getPatient() {
        return patient;
    }

    public void setPatient(UpdatedPatient patient) {
        this.patient = patient;
    }
}
