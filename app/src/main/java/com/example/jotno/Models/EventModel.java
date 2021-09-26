package com.example.jotno.Models;

public class EventModel {

    private String eventTag;
    private String message;

    public EventModel(String response, String message) {

        this.eventTag = response;
        this.message = message;

    }

    public boolean isTagMatchWith(String tag){
        return eventTag.equals(tag);
    }

    public String getMessage() {
        return message;
    }
}
