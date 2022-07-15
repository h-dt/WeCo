//package com.dreamteam.hola.config.oauth.provider;
//
//import java.util.Map;
//
//public class GoogleUserInfo implements OAuth2UserInfo{
//
//    private Map<String,Object> attributes;
//
//    public GoogleUserInfo(Map<String,Object>attributes){
//        this.attributes = attributes;
//
//    }
//
//    @Override
//    public String getProviderId() {
//        return (String) attributes.get("sub");
//    }
//
//    @Override
//    public String getProvider() {
//        return"Google";
//    }
//
//    @Override
//    public String getEmail() {
//        return (String) attributes.get("email");
//    }
//
//    @Override
//    public String getName() {
//        return (String) attributes.get("name");
//    }
//
//
//    @Override
//    public Map<String, Object> getAttributes() {
//        return attributes;
//    }
//
//    @Override
//    public String getProfileImage() {
//        return (String) attributes.get("picture");
//
//    }
//
//    @Override
//    public String nickname() {
//        return getEmail().substring(0,getEmail().indexOf("@"));
//    }
//}
