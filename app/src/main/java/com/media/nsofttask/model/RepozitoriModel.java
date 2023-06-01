package com.media.nsofttask.model;

public class RepozitoriModel {

    private final String id;
    private final String avatarurl;
    private final String owner;
    private final String name;
    private final String description;
    private final String star;
    private final String forks;
    private final String issues;
    private final String watchers;
    private final boolean favorite;

    //public ListModel(){};

    public RepozitoriModel(String id, String avatarurl, String owner, String name, String description, String star, String forks, String issues,
                           String watchers, boolean favorite) {
        this.id = id;
        this.avatarurl = avatarurl;
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.star = star;
        this.forks = forks;
        this.issues = issues;
        this.watchers = watchers;
        this.favorite = favorite;
    }

    public String getId() {
        return id;
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

    public boolean getFavorite() {
        return favorite;
    }


}


