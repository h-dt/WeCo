package com.dreamteam.hola.config.oauth;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.dao.MemberMapper;
import com.dreamteam.hola.domain.Member;
import com.dreamteam.hola.domain.Role;
import com.dreamteam.hola.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {


    private final MemberMapper memberMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest : "+userRequest.getClientRegistration());
        System.out.println("getAccessToken : "+userRequest.getAccessToken().getTokenValue());
        System.out.println("userRequest : "+userRequest.getClientRegistration().getRegistrationId());
        System.out.println("loadUser : "+super.loadUser(userRequest).getAttributes());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("getAttributes"+oAuth2User.getAttributes());

        //회원가입 강제로 진행
        String provider = userRequest.getClientRegistration().getClientId();//google
        String providerId = oAuth2User.getAttribute("sub");//google의 sub
        String email = oAuth2User.getAttribute("email");
        String username = provider+"_"+providerId;//google_sub(pk)
        String password = UUID.randomUUID().toString();
        String defaultImage = oAuth2User.getAttribute("picture");
        String str = oAuth2User.getAttribute("email");
        String nickname = str.substring(0, str.indexOf("@"));

        Member member = memberMapper.findByUsername(username);
        if(member == null){
            Member domain = Member.builder()
                    .username(username)
                    .password(password)
                    .nickname(nickname)
                    .email(email)
                    .role(Role.ROLE_USER)
                    .profileImage(defaultImage)
                    .socialType(provider)
                    .build();
            memberMapper.joinMember(domain);

        }


        return new PrincipalDetails(member,oAuth2User.getAttributes());
    }
}
