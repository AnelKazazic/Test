package com.media.nsofttask.model;

public class ContributorsDBModel {

    private final String avatarurl;
    private final String contributor_name;


    public ContributorsDBModel(String avatarurl, String contributor_name) {
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
