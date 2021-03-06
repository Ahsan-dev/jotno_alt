package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DaysDatum {

    @SerializedName("name")
    @Expose
    private String name;

    public DaysDatum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
