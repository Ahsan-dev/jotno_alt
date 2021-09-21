
package com.example.jotno.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PrescriptionResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("age")
    @Expose
    private String age;

    @SerializedName("prescription")
    @Expose
    private Prescription prescription;
    @SerializedName("initial_tests")
    @Expose
    private List<InitialTest> initialTests = null;
    @SerializedName("main_test")
    @Expose
    private List<MainTest> mainTest = null;
    @SerializedName("medicine")
    @Expose
    private List<Medicine> medicine = null;

    public String getStatus() {
        return status;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public List<InitialTest> getInitialTests() {
        return initialTests;
    }

    public void setInitialTests(List<InitialTest> initialTests) {
        this.initialTests = initialTests;
    }

    public List<MainTest> getMainTest() {
        return mainTest;
    }

    public void setMainTest(List<MainTest> mainTest) {
        this.mainTest = mainTest;
    }

    public List<Medicine> getMedicine() {
        return medicine;
    }

    public void setMedicine(List<Medicine> medicine) {
        this.medicine = medicine;
    }

}
