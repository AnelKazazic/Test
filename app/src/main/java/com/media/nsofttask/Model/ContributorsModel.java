package com.media.nsofttask.Model;

public class ContributorsModel {

    private final String contributors;
    private final String contributor_name;

    private final boolean favorite;

    public ContributorsModel(String contributors, String contributor_name, boolean favorite) {
        this.contributors = contributors;
        this.contributor_name = contributor_name;
        this.favorite = favorite;
    }

    public String getContributors() {
        return contributors;
    }

    public String getContributor_name() {
        return contributor_name;
    }

    public boolean isFavorite() {
        return favorite;
    }


}
