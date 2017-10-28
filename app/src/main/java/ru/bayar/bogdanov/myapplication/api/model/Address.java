package ru.bayar.bogdanov.myapplication.api.model;


import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("street")
    private String mStreet;
    @SerializedName("suite")
    private String mSuite;
    @SerializedName("city")
    private String mCity;
    @SerializedName("zipcode")
    private String mZipCode;
    @SerializedName("geo")
    private Geo mGeo;

    public String getStreet() {
        return mStreet;
    }

    public String getSuite() {
        return mSuite;
    }

    public String getCity() {
        return mCity;
    }

    public String getZipCode() {
        return mZipCode;
    }

    public Geo getGeo() {
        return mGeo;
    }
}
