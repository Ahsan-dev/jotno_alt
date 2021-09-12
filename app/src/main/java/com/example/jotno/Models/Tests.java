package com.example.jotno.Models;

public class Tests {

    private String prescriptionNo, testName, testDate;

    public Tests(String prescriptionNo, String testName, String testDate) {
        this.prescriptionNo = prescriptionNo;
        this.testName = testName;
        this.testDate = testDate;
    }

    public String getPrescriptionNo() {
        return prescriptionNo;
    }

    public void setPrescriptionNo(String prescriptionNo) {
        this.prescriptionNo = prescriptionNo;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }
}
