package ru.bayar.bogdanov.myapplication.api.model;


import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("username")
    private String mUsername;
    @SerializedName("email")
    private String email;
    @SerializedName("address")
    private Address mAddress;
    @SerializedName("phone")
    private String mPhone;
    @SerializedName("website")
    private String mWebsite;
    @SerializedName("company")
    private Company mCompany;

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return mAddress;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public Company getCompany() {
        return mCompany;
    }
}
