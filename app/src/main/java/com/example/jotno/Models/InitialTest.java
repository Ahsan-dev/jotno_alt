
package com.example.jotno.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class InitialTest {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("result")
    @Expose
    private String result;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
