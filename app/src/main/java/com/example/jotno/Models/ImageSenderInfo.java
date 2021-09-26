package com.example.jotno.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ImageSenderInfo implements Parcelable {

    @SerializedName("test_name")
    private String testName;


    public ImageSenderInfo() {
    }

    public ImageSenderInfo(String testName) {
        this.testName = testName;
    }

    public final static Parcelable.Creator<ImageSenderInfo> CREATOR = new Creator<ImageSenderInfo>() {

        @SuppressWarnings({
                "unchecked"
        })
        public ImageSenderInfo createFromParcel(Parcel in) {
            ImageSenderInfo instance = new ImageSenderInfo();
            instance.testName = ((String) in.readValue((String.class.getClassLoader())));

            return instance;
        }

        public ImageSenderInfo[] newArray(int size) {
            return (new ImageSenderInfo[size]);
        }

    };


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(testName);

    }

    public int describeContents() {
        return  0;
    }

}
