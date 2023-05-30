package com.media.nsofttask.Model;

public class FavoriteModel {

    private final String avatarurl;
    private final String owner;
    private final String name;
    private final String description;
    private final String star;
    private final String forks;
    private final String issues;
    private final String watchers;

    public FavoriteModel(String avatarurl, String owner, String name, String description, String star,
                         String forks, String issues, String watchers) {
        this.avatarurl = avatarurl;
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.star = star;
        this.forks = forks;
        this.issues = issues;
        this.watchers = watchers;
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public String getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStar() {
        return star;
    }

    public String getForks() {
        return forks;
    }

    public String getIssues() {
        return issues;
    }

    public String getWatchers() {
        return watchers;
    }


}
