package com.dreamteam.hola.util.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthenticationFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private JwtTokenProvider jwtTokenProvider;

    // security 쪽에서 주는 걸 주입 받기위해 생성자 선언 이걸 RequireArgsConstructor로 바꿀 수 있을 지 생각해보기
    // JwtTokenProvider가 @Component로 Spring 관리 대상이라 가능 할 것으로 예상
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Request로 들어오는 Jwt Token의 유효성을 검증(jwtTokenProvider.validateToken)하는 filter를 filterChain에 등록
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        // 토큰 검증을 통과한 유효한 토큰일 경우 권한을 부여함
        if(token != null && jwtTokenProvider.validateToken(token)) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            // Security가 관리할 수 있도록 넣어줌
            SecurityContextHolder.getContext().setAuthentication(auth);
            logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", auth.getName(), ((HttpServletRequest) request).getRequestURI());
        } else {
            logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", ((HttpServletRequest) request).getRequestURI());
        }
        filterChain.doFilter(request, response);
    }
}
