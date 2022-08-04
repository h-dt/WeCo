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

import javax.servlet.ServletException;
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
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        log.info("Principal에서 꺼낸 OAuth2User = {}", oAuth2User);

        // oAuth2User를 우리의 memberDto 로 변환하는 로직



        log.info("토큰 발행 시작");

        Token token = tokenProvider.createtoken("아무거나 username", Role.ROLE_USER); // username 넣어줘야함
//        log.info("token = {}", token);
//        String targetUrl = UriComponentsBuilder.fromUriString("/")
//                .queryParam("token", token)
//                .build().toUriString();
//        getRedirectStrategy().sendRedirect(request,response,targetUrl);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        String result = objectMapper.writeValueAsString(token);
        response.getWriter().write(result);
    }
}