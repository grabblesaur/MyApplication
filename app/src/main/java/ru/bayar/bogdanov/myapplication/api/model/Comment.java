package ru.bayar.bogdanov.myapplication.api.model;


import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("postId")
    private int mPostId;
    @SerializedName("id")
    private int mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("body")
    private String mBody;

    public int getPostId() {
        return mPostId;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getBody() {
        return mBody;
    }
}
