package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BlogBody {

    @SerializedName("data")
    @Expose
    private List<BlogDatum> data = null;

    public List<BlogDatum> getData() {
        return data;
    }

    public void setData(List<BlogDatum> data) {
        this.data = data;
    }

}
