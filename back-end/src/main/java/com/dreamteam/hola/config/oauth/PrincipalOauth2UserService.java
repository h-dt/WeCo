package com.dreamteam.hola.config.oauth;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.config.oauth.provider.GitHubUserInfo;
import com.dreamteam.hola.config.oauth.provider.GoogleUserInfo;
import com.dreamteam.hola.config.oauth.provider.KakaoUserInfo;
import com.dreamteam.hola.config.oauth.provider.OAuth2UserInfo;
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

        OAuth2UserInfo oAuth2UserInfo = null;
        if (userRequest.getClientRegistration().getRegistrationId().equals("google")){
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());

        }else if(userRequest.getClientRegistration().getRegistrationId().equals("github")){
            oAuth2UserInfo = new GitHubUserInfo(oAuth2User.getAttributes());

        }else if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")){
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }
        //회원가입 강제로 진행
        String provider = oAuth2UserInfo.getProvider();//google
        String providerId = oAuth2UserInfo.getProviderId();//google의 sub
        String email = oAuth2UserInfo.getEmail();
        String username = provider+"_"+providerId;;//google_sub(pk)
        String password = UUID.randomUUID().toString();
        String profileImage = oAuth2UserInfo.getProfileImage();
        String nickname = email.substring(0, email.indexOf("@"));

        Member member = memberMapper.findByUsername(username);

        if(member == null){
            Member domain = Member.builder()
                    .username(username)
                    .password(password)
                    .nickname(nickname)
                    .email(email)
                    .role(Role.ROLE_USER)
                    .profileImage(profileImage)
                    .socialType(provider)
                    .build();
            memberMapper.joinMember(domain);

        }else {
            System.out.println("이미 회원가입한 적이있습니다");
        }


        return super.loadUser(userRequest);
    }
}
