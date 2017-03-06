package com.codepath.apps.mysimletweets.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hongnhung on 05/03/2017.
 */

public class Tweet {
    @SerializedName("id")
    private long id;

    @SerializedName("text")
    private String text;


    @SerializedName("user")
    private User user;


    @SerializedName("profile_image_url_https")
    private String ProfileImageUrl;


    public String getProfileImageUrl() {
        return ProfileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        ProfileImageUrl = profileImageUrl;
    }

    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
