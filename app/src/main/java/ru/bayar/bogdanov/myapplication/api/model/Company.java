package ru.bayar.bogdanov.myapplication.api.model;


import com.google.gson.annotations.SerializedName;

public class Company {
    @SerializedName("name")
    private String mName;
    @SerializedName("catchPhrase")
    private String mCatchPhrase;
    @SerializedName("bs")
    private String mBs;

    public String getName() {
        return mName;
    }

    public String getCatchPhrase() {
        return mCatchPhrase;
    }

    public String getBs() {
        return mBs;
    }
}
