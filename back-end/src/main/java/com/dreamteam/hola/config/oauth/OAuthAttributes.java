//package com.dreamteam.hola.config.oauth;
//
//import lombok.Builder;
//import lombok.Getter;
//
//import java.util.Map;
//
//@Getter
//public class OAuthAttributes {
//
//    private Map<String,Object> attributes;
//    private String nameAttributeKey;
//    private String name;
//    private String email;
//    private String profileImage;
//
//    @Builder
//    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String profileImage) {
//        this.attributes = attributes;
//        this.nameAttributeKey = nameAttributeKey;
//        this.name = name;
//        this.email = email;
//        this.profileImage = profileImage;
//    }
//
//    public static OAuthAttributes of(String registrationId,
//                                     String userNameAttributeName,
//                                     Map<String,Object>attributes){
//        return ofGoogle(userNameAttributeName,attributes);
//    }
//    private static OAuthAttributes ofGoogle(String userNameAttributeName,
//                                            Map<String,Object>attributes){
//        return OAuthAttributes.builder()
//                .name(String.valueOf(attributes.get("name")))
//                .email(String.valueOf(attributes.get("email")))
//                .profileImage(String.valueOf(attributes.get("profileImage")))
//                .attributes(attributes)
//                .nameAttributeKey(userNameAttributeName)
//                .build();
//    }
//}
