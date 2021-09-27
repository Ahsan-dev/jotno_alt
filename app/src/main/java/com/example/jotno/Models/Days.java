package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Days {

    @SerializedName("data")
    @Expose
    private List<DaysDatum> data = null;

    public Days(List<DaysDatum> data) {
        this.data = data;
    }

    public List<DaysDatum> getData() {
        return data;
    }

    public void setData(List<DaysDatum> data) {
        this.data = data;
    }

}
