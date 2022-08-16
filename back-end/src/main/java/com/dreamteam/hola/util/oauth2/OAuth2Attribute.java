package com.dreamteam.hola.util.oauth2;

import com.dreamteam.hola.domain.Role;
import com.dreamteam.hola.dto.member.MemberDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@ToString
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class OAuth2Attribute {
    private Map<String, Object> attributes;
    private String attributeKey;
    private String email;
    private String name;
    private String picture;
    private Role role;

    static OAuth2Attribute of(String provider, String attributeKey, Map<String, Object> attributes) {
        switch (provider) {
            case "google" :
                return ofGoogle(attributeKey, attributes);
            case "kakao" :
                return ofKakao("email", attributes);
            default :
                throw new RuntimeException();
        }
    }


    private static OAuth2Attribute ofGoogle(String attributeKey, Map<String, Object> attributes) {
        return OAuth2Attribute.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
//                .role(attributes.get(""))
                .attributes(attributes)
                .attributeKey(attributeKey)
                .build();
    }

    private static OAuth2Attribute ofKakao(String attributeKey, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuth2Attribute.builder()
                .name((String) kakaoProfile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .picture((String) kakaoProfile.get("profile_image_url"))
//                .role((String) attributes.get(""))
                .attributes(kakaoAccount)
                .attributeKey(attributeKey)
                .build();
    }
    MemberDto convertToMember(String provider) {
        return MemberDto.builder()
                .email(email)
                .nickname(provider+"_"+email.split("@")[0])
                .profileImage(picture)
                .role(role)
                .socialType(provider)
                .build();
    }
}