package com.codepath.apps.mysimletweets.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hongnhung on 05/03/2017.
 */

public class User {

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;


    @SerializedName("profile_image_url")
    private String ProfileImage;

    public String getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(String profileImage) {
        ProfileImage = profileImage;
    }

    public String getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(String createAt) {
        CreateAt = createAt;
    }

    @SerializedName("created_at")

    protected String CreateAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
