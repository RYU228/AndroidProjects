package com.example.recyclerview;

public class Profile {
    private int profile;
    private String name;
    private String content;

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Profile(int profile, String name, String content) {
        this.profile = profile;
        this.name = name;
        this.content = content;
    }
}
