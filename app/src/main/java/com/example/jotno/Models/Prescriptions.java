package com.example.jotno.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prescriptions {

    @SerializedName("prescription_list")
    @Expose
    private PrescriptionList prescriptionList;

    public PrescriptionList getPrescriptionList() {
        return prescriptionList;
    }

    public void setPrescriptionList(PrescriptionList prescriptionList) {
        this.prescriptionList = prescriptionList;
    }


}
