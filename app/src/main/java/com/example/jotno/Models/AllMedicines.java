package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllMedicines {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("medicine")
    @Expose
    private List<AlMedicineBody> medicine = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AlMedicineBody> getMedicine() {
        return medicine;
    }

    public void setMedicine(List<AlMedicineBody> medicine) {
        this.medicine = medicine;
    }

}
