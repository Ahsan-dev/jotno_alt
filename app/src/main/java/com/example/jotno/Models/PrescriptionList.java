package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PrescriptionList {

    @SerializedName("data")
    @Expose
    private List<PrescriptionsDatum> data = null;

    public List<PrescriptionsDatum> getData() {
        return data;
    }

    public void setData(List<PrescriptionsDatum> data) {
        this.data = data;
    }
}
