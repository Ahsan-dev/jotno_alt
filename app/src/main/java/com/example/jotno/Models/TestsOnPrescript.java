package com.example.jotno.Models;

public class TestsOnPrescript {

    private String testName, testValue;

    public TestsOnPrescript(String testName, String testValue) {
        this.testName = testName;
        this.testValue = testValue;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestValue() {
        return testValue;
    }

    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }
}
