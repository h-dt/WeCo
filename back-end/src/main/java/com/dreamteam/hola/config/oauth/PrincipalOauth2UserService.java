package com.dreamteam.hola.config.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest : "+userRequest.getClientRegistration());
        System.out.println("getAccessToken : "+userRequest.getAccessToken().getTokenValue());
        System.out.println("userRequest : "+userRequest.getClientRegistration().getRegistrationId());
        System.out.println("loadUser : "+super.loadUser(userRequest).getAttributes());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        //회원가입 강제로 진행

        return super.loadUser(userRequest);
    }
}
