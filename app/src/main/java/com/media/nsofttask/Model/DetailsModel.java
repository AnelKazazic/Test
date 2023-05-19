package com.media.nsofttask.Model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.OffsetDateTime;

public class DetailsModel {

    private final String avatarurl;
    private final String owner;
    private final String name;
    private final String description;
    private final String star;
    private final String forks;
    private final String issues;
    private final String watchers;
    private final String branch;
    private final String created;
    private final String updated;
    private final String html;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DetailsModel(String avatarurl, String owner, String name, String description, String star, String forks,
                        String issues, String watchers, String branch, String created, String updated, String html) {
        this.avatarurl = avatarurl;
        this.owner = owner;
        this.name = name;
        this.description = description;
        this.star = star;
        this.forks = forks;
        this.issues = issues;
        this.watchers = watchers;
        this.branch = branch;
        this.created = created;
        this.updated = updated;
        this.html = html;
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

    public String getBranch() {
        return branch;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    public String getHtml() {
        return html;
    }

}
