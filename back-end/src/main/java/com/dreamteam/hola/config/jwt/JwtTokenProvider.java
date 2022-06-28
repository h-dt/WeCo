//package com.dreamteam.hola.config.jwt;
//
//import com.dreamteam.hola.domain.Role;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import java.time.ZonedDateTime;
//import java.util.Base64;
//import java.util.Date;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Component
//public class JwtTokenProvider {
//
//    @Value("spring.jwt.secret")
//    private String secretKey;
//
//    private long expire = 60*24*30;     //1month
//
//    private final UserDetailsService userDetailsService;
//
//    @PostConstruct
//    protected void init(){
//        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//    }
//
//    //jwt 토큰 생성
//    public String generationToken(String userPk)throws Exception{
//        Claims claims = Jwts.claims().setSubject(userPk);
//        Date now = new Date();
//        return Jwts.builder()
//                .setIssuedAt(now)//토큰 발행일자
//                .setExpiration(new Date(now.getTime()+expire))//set Expire Time
//                .setClaims(claims)// 데이터
//                .signWith(SignatureAlgorithm.HS256,secretKey)//암호화 알고리즘,secret값 세팅
//                .compact();
//    }
//
//    //jwt 토큰으로 인증 정보를 조회
//    public Authentication getAuthentication(String token){
//        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
//        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
//    }
//    //jwt 토큰에서 회원 구별 정보 추출
//    public String getUserPk(String token){
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
//    }
//    //request의 Header 에서 token 파싱 : "X-AUTH TOKEN:jwt토큰"
//    public String resolveToken(HttpServletRequest req){
//        return req.getHeader("X-AUTH-TOKEN");
//    }
//    public boolean validateToken(String jwtToken){
//        try{
//            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
//            return !claims.getBody().getExpiration().before(new Date());
//        }catch (Exception e){
//            return false;
//        }
//    }
//
//}
