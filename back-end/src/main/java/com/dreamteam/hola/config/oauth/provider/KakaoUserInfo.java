package com.dreamteam.hola.config.oauth.provider;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo{


    private Map<String,Object> attributes;

    private Map<String,Object> attributesProperties;
    private Map<String,Object> attributesAccount;
    private Map<String,Object> attributesProfile;


    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.attributesAccount = (Map<String, Object>) attributes.get("kakao_account");
        this.attributesProfile = (Map<String, Object>) attributesAccount.get("profile");
        this.attributesProperties = (Map<String, Object>)  attributes.get("properties");
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }



    @Override
    public String getProfileImage() {
        return String.valueOf(attributesProfile.get("profile_image_url"));
    }

    @Override
    public String nickname() {
        return String.valueOf(attributesProperties.get("nickname"));
    }

    @Override
    public String getProviderId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getProvider() {
        return "Kakao";
    }

    @Override
    public String getEmail() {
        return String.valueOf(attributesAccount.get("email"));
    }

    @Override
    public String getName() {
        return String.valueOf(attributesProperties.get("nickname"));
    }
}
