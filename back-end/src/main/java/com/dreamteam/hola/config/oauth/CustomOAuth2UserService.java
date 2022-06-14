//package com.dreamteam.hola.config.oauth;
//
//import com.dreamteam.hola.dao.MemberMapper;
//import com.dreamteam.hola.domain.Member;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import javax.servlet.http.HttpSession;
//
//@Service
//@RequiredArgsConstructor
//public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//
//    private final MemberMapper memberMapper;
//    private final HttpSession httpSession;
//
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//
//        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate  = new DefaultOAuth2UserService();
//        OAuth2User oAuth2User = delegate.loadUser(userRequest);
//
//        String socialType = userRequest.getClientRegistration().getRegistrationId();//google,cacao
//        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
//
//        OAuthAttributes attributes = OAuthAttributes.of(socialType, userNameAttributeName, oAuth2User.getAttributes());
//
//
//
//
//    }
//
//    private Member saveOrUpdate(OAuthAttributes attributes){
//        Member member = memberMapper.findByEmail(attributes.getEmail());
//
//
//
//    }
//}
