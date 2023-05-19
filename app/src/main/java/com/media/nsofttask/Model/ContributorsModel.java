package com.media.nsofttask.Model;

public class ContributorsModel {

    private final String contributors;
    private final String contributor_name;

    public ContributorsModel(String contributors, String contributor_name) {
        this.contributors = contributors;
        this.contributor_name = contributor_name;
    }

    public String getContributors() {
        return contributors;
    }

    public String getContributor_name() {
        return contributor_name;
    }


}
