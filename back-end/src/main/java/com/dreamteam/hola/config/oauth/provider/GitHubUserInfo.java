package com.dreamteam.hola.config.oauth.provider;

import java.util.Map;

public class GitHubUserInfo implements OAuth2UserInfo{
    private Map<String,Object> attributes;

    public GitHubUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return null;
    }

    @Override
    public String getProvider() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
