package com.example.amanuel.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class Cat {
    @SerializedName("title")
    private String title;
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("image_url")
    private String image_url;
    @SerializedName("description")
    private String description;


    public String getTitle() {
        return title;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getDescription() {
        return description;
    }
}