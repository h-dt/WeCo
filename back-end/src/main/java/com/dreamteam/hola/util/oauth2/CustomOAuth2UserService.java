package com.dreamteam.hola.util.oauth2;

import com.dreamteam.hola.dao.MemberMapper;
import com.dreamteam.hola.domain.Member;
import com.dreamteam.hola.dto.member.MemberDto;
import com.dreamteam.hola.dto.member.MemberReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberMapper memberMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // DefaultOAuth2UserService 객체를 성공 정보를 바탕으로 만듬
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        // 생성된 Service 객체로 부터 User를 받는다.
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        /* 받은 User로 user정보를 받는다. */
        // Oauth2 서비스 id 구분 (구글, 카카오, 네이버)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // Oauth2 로그인 진행 시 key가 되는 필드 값 (pk) (구글은 sub)
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        log.info("registrationId = {}", registrationId);
        log.info("userNameAttributeName = {}", userNameAttributeName);

        // SuccessHandler가 사용할 수 있도록 서비스 등록 (Attributes에서 자세한 사용자 정보를 담고 있다)
        OAuth2Attribute oAuth2Attribute = OAuth2Attribute.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        MemberDto member = oAuth2Attribute.convertToMember(registrationId);
        saveOrUpdate(member);

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")), oAuth2Attribute.getAttributes(), oAuth2Attribute.getAttributeKey());
    }

    private void saveOrUpdate(MemberDto memberDto){
        MemberDto findMemberDto = memberMapper.findByEmail(memberDto.getEmail());

        if(findMemberDto == null){
            memberMapper.signup(memberDto);
        }else {
            memberMapper.update(memberDto);
        }
    }
}