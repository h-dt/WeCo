package com.dreamteam.hola.util.oauth2;

import com.dreamteam.hola.domain.Role;
import com.dreamteam.hola.util.jwt.JwtTokenProvider;
import com.dreamteam.hola.util.jwt.Token;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenProvider tokenProvider;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        log.info("Principal에서 꺼낸 OAuth2User = {}", oAuth2User);
        System.out.println("============" + oAuth2User.getAttributes());

        String nickname = ((String) oAuth2User.getAttributes().get("email")).split("@")[0];
        System.out.println("nickname = " + nickname);

        log.info("토큰 발행 시작");

        Token token = tokenProvider.createtoken(nickname, Role.ROLE_USER); // username 넣어줘야함

        System.out.println("token.getAccessToken() = " + token.getAccessToken());
        System.out.println("token.getRefreshToken() = " + token.getRefreshToken());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        String result = objectMapper.writeValueAsString(token);
        response.getWriter().write(result);
    }
}