package ru.bayar.bogdanov.myapplication.api.model;


import com.google.gson.annotations.SerializedName;

public class Geo {
    @SerializedName("lat")
    private String mLatitude;
    @SerializedName("lng")
    private String mLongitude;

    public String getLatitude() {
        return mLatitude;
    }

    public String getLongitude() {
        return mLongitude;
    }
}
