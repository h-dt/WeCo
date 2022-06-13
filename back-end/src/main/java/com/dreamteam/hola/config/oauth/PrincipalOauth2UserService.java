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

//구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {


    private final MemberMapper memberMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("getClientRegistration : "+userRequest.getClientRegistration());//registration로 어떤 OAuth로 로그인 하였는지 확인가능.
        System.out.println("getAccessToken : "+userRequest.getAccessToken().getTokenValue());
        //구글 로그인 버튼 -> 구글 로그인 창 -> 로그인 완료 -> code를 리턴 받는데(OAuth-Client 라이브러리가 대신받음) -> AccessTocken 요청
        //userRequest 정보 -> loadUser 함수 -> 구글로부터 회원 프로필 받음

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("getAttributes :"+oAuth2User.getAttributes());


        //회원가입 강제로 진행
        String provider = userRequest.getClientRegistration().getRegistrationId();//google
        String providerId = oAuth2User.getAttribute("sub");//google의 sub
        String email = oAuth2User.getAttribute("email");
        String username = provider+"_"+providerId;;//google_sub(pk)
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


        return super.loadUser(userRequest);
    }
}
