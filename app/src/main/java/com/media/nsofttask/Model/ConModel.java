package com.media.nsofttask.Model;

public class ConModel {

    private final String avatarurl;
    private final String contributor_name;


    public ConModel(String avatarurl, String contributor_name) {
        this.avatarurl = avatarurl;
        this.contributor_name = contributor_name;

    }

    public String getAvatarurl() {
        return avatarurl;
    }


    public String getContributor_name() {
        return contributor_name;
    }



}
