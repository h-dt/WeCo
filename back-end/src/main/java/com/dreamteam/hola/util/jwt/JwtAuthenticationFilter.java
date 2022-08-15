package com.dreamteam.hola.util.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {


    private final JwtTokenProvider jwtTokenProvider;


    // Request로 들어오는 Jwt Token의 유효성을 검증(jwtTokenProvider.validateToken)하는 filter를 filterChain에 등록
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        // 토큰 검증을 통과한 유효한 토큰일 경우 권한을 부여함
        try{
            if(token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                // Security가 관리할 수 있도록 넣어줌
                SecurityContextHolder.getContext().setAuthentication(auth);
                log.info("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", auth.getName(), ((HttpServletRequest) request).getRequestURI());
            }
        } catch (SignatureException | MalformedJwtException | IllegalArgumentException e) { // 형식, 길이, 변조
            log.info("형식, 길이, 변조 error");
            e.printStackTrace();
            request.setAttribute("exception", ExceptionCode.WRONG_TYPE_TOKEN.getMessage());
        } catch (ExpiredJwtException e) {   // 만료
            log.info("만료 error");
            e.printStackTrace();
            request.setAttribute("exception", ExceptionCode.EXPIRED_TOKEN.getMessage());
        } catch (UnsupportedJwtException e) {
            log.info("지원 error");
            e.printStackTrace();
            request.setAttribute("exception", ExceptionCode.UNSUPPORTED_TOKEN.getMessage());
        } catch(Exception e) {
            log.info("================================================");
            log.info("JwtFilter - doFilterInternal() 오류발생");
            log.info("token : {}", token);
            log.info("Exception Message : {}", e.getMessage());
            log.info("Exception StackTrace : {");
            e.printStackTrace();
            log.info("}");
            log.info("================================================");
            request.setAttribute("exception", ExceptionCode.UNKNOWN_ERROR.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}