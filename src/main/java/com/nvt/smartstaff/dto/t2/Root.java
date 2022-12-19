package com.nvt.smartstaff.dto.t2;

import java.util.ArrayList;

public class Root{
    public int id;
    public String nickname;
    public String gravatar_id;
    public String github_profile;
    public String twitter_profile;
    public int contributions_count;
    public ArrayList<Organisation> organisations;
    public String link;
    public ArrayList<PullRequest> pull_requests;
}
