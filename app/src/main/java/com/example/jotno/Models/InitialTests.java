package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InitialTests {

    @SerializedName("data")
    @Expose
    private List<Datum__1> data = null;

    public List<Datum__1> getData() {
        return data;
    }

    public void setData(List<Datum__1> data) {
        this.data = data;
    }

}
