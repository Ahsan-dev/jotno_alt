package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BannerBody {

    @SerializedName("data")
    @Expose
    private List<BannerDatum> data = null;

    public List<BannerDatum> getData() {
        return data;
    }

    public void setData(List<BannerDatum> data) {
        this.data = data;
    }

}
