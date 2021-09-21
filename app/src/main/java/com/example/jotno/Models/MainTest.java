
package com.example.jotno.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MainTest {


    @SerializedName("test_type")
    @Expose
    private String testType;
    @SerializedName("test_type_list")
    @Expose
    private List<TestType> testTypeList = null;

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public List<TestType> getTestTypeList() {
        return testTypeList;
    }

    public void setTestTypeList(List<TestType> testTypeList) {
        this.testTypeList = testTypeList;
    }

}
