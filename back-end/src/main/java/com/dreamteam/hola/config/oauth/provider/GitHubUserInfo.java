package com.dreamteam.hola.config.oauth.provider;

import java.util.Map;

public class GitHubUserInfo implements OAuth2UserInfo{
    private Map<String,Object> attributes;

    public GitHubUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getProviderId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getProvider() {
        return "GitHub";
    }

    @Override
    public String getEmail() {
        String id = String.valueOf( attributes.get("id"));
        return  attributes.get("email") == null ? id+"@github.com" : (String) attributes.get("email");
    }

    @Override
    public String profileImage() {
        return (String) attributes.get("avatar_url");
    }

    @Override
    public String getName() {
        return (String) attributes.get("node_id");
    }

    @Override
    public String nickname() {
        return (String)attributes.get("login");
    }
}
