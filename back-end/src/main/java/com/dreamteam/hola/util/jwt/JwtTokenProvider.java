package com.dreamteam.hola.util.jwt;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.domain.Role;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${spring.jwt.secret}")
    private String secretKey;

    // 1시간 동안 유효 (Value 방식으로 변경할 수도 있음
    private static final long tokenPeriod = 1000L * 60 * 60;
    private static final long refreshPeriod = 1000L * 60 * 60 * 24 * 30 * 3;

    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // JWT token 생성
    public Token createtoken(String nickname, Role role) {
// token에 들어갈 정보들을 가공
        Claims claims = Jwts.claims().setSubject(nickname);
        claims.put("role", role);
        Date now = new Date();
        return new Token(
                Jwts.builder()
                        .setClaims(claims) // 데이터
                        .setIssuedAt(now) // 발행 일자
                        .setExpiration(new Date(now.getTime() + tokenPeriod)) // 만기 일자
                        .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘 및 secret 값 세팅
                        .compact(),
                Jwts.builder()
                        .setClaims(claims) // 데이터
                        .setIssuedAt(now) // 발행 일자
                        .setExpiration(new Date(now.getTime() + refreshPeriod)) // 만기 일자
                        .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘 및 secret 값 세팅
                        .compact());
    }

    // Jwt token으로 인증 정보를 조회
    public Authentication getAuthentication(String token) {
        PrincipalDetails principalDetails = (PrincipalDetails) userDetailsService.loadUserByUsername(this.getUsername(token));
        return new UsernamePasswordAuthenticationToken(principalDetails, "", principalDetails.getAuthorities());
    }

    // Jwt token으로 인증 정보 조회 시 회원 정보 추출
    public String getUsername(String token) {
// token 내부의 claims에 저장된 정보 복호화
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 파싱 : "X-AUTH-TOKEN : jwt 토큰 값"
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    // Jwt token의 유효성 및 만료일자 확인
    public boolean validateToken(String token) {
//        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
//        } catch (MalformedJwtException e) {
//            logger.info("잘못된 JWT 서명입니다.");
//        } catch (ExpiredJwtException e) {
//            logger.info("만료된 JWT 토큰입니다.");
//        } catch (UnsupportedJwtException e) {
//            logger.info("지원되지 않는 JWT 토큰입니다.");
//        } catch (IllegalArgumentException e) {
//            logger.info("JWT 토큰이 잘못되었습니다.");
//        }
//        return false;
    }
}