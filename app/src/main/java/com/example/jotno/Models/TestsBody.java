package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestsBody {

    @SerializedName("data")
    @Expose
    private List<TestsDatum> data = null;

    public List<TestsDatum> getData() {
        return data;
    }

    public void setData(List<TestsDatum> data) {
        this.data = data;
    }

}
