package ru.bayar.bogdanov.myapplication.api.model;


import com.google.gson.annotations.SerializedName;

public class TodoObject {
    @SerializedName("userId")
    private int mUserId;
    @SerializedName("id")
    private int mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("completed")
    private boolean mIsCompleted;

    public int getUserId() {
        return mUserId;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public boolean isCompleted() {
        return mIsCompleted;
    }
}
