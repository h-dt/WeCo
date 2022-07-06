//package com.dreamteam.hola.config.jwt;
//
//import com.dreamteam.hola.config.auth.PrincipalDetails;
//import com.dreamteam.hola.domain.Member;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@RequiredArgsConstructor
//@Log4j2
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final AuthenticationManager authenticationManager;
//
//    /** 로그인 요청을 하면 로그인 시도를 위해서 실행되는 함수 */
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        log.info("로그인 시도 : JwtAuthentication.attemptAuthentication");
//        ObjectMapper om = new ObjectMapper();
//        try {
//            //1.username,pw를 받는다.
//            log.info("1.username,pw 받는다.");
//            LoginReq login = om.readValue(request.getInputStream(), LoginReq.class);
//            log.info(login.toString());
//
//            //username,pw를 이용하여 token 발급
//            UsernamePasswordAuthenticationToken authenticationToken =
//                    new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
//            log.info(authenticationToken.getPrincipal().toString());
//            log.info(authenticationToken.getCredentials().toString());
//            log.info("===============================\n");
//
//            //2. 정상적인 로그인 여부를 검증
//            log.info("2. 정상적인 로그인 시도 여부를 검증");
//            /** 로그인 정보를 가지고 임시로 Auth 토큰을 생성해서 인증을 확인
//                DI 받은 authenticationManager로 로그인을 시도한다
//                DetailsService를 상속받은 PrincipalDetailsService가 호출되고 loadUserByUsername() 함수를 실행
//                authenticate()에 토큰을 넘기면 PrincipalDetailsService.class -> loadUserByusername()메소드가 실행
//                DB에 저장되어 있는 username & password가 일치하면 authentication이 생성*/
//            log.info("-> Authenticate Start");
//            Authentication authenticate =
//                    authenticationManager.authenticate(authenticationToken);
//            log.info("<- Authentication End");
//            log.info("======================================================");
//            // PrincipalDetails 를 세션에 저장(권한 관리를 위해서 세션에저장)
//            // JWT 토큰을 만들어서 응답
//
//            //3. 로그인 성공
//            log.info("로그인 성공");
//            //Authentication에 있는 인증된 Principal 객체를 PrincipalDetails 객체로 꺼낸다.
//            PrincipalDetails principalDetails = (PrincipalDetails) authenticate.getPrincipal();
//            log.info("username : "+principalDetails.getMember().getUsername());
//            log.info("password : "+principalDetails.getMember().getPassword());
//            log.info("=====================================");
//
//            //4. authentication을 반환
//            log.info("authenticatioin 반환");
//            return authenticate;
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return super.attemptAuthentication(request, response);
//
//    }
//    /** attemptAuthentication()에서 인증이 성공되면 수행되는 메서드, JWT 발급 */
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        log.info("인증 완료 : JwtAuthenticationFilter.successfulAuthentication");
//        //5. JWT 발급
//
//        super.successfulAuthentication(request, response, chain, authResult);
//    }
//}
//
//
//
