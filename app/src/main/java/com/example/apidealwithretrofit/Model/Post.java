package com.example.apidealwithretrofit.Model;

import com.google.gson.annotations.SerializedName;

public class Post {

    // here we put each attribute in json object
    private int userId;
    private int id;
    private String title;

    // in the json file this attribute call body in here i call it text --> so we just change it by SerializedName
    @SerializedName("body")
    private String text;




    // and just make getter and setter
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
