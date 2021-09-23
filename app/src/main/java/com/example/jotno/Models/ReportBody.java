package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReportBody {

    @SerializedName("data")
    @Expose
    private List<ReportDatum> data = null;

    public List<ReportDatum> getData() {
        return data;
    }

    public void setData(List<ReportDatum> data) {
        this.data = data;
    }

}
