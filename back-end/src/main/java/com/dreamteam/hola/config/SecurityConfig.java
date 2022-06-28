package com.dreamteam.hola.config;

import com.dreamteam.hola.config.jwt.CustomFilter;
import com.dreamteam.hola.config.jwt.JwtAuthenticationFilter;
import com.dreamteam.hola.config.oauth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity//시큐리티 필터가 등록이 된다.(내부에 @Configure 내장)
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)//@secured 어노테이션 활성화,@preAuthorize 활성화
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //시큐리티가 대신 로그인을 해줌.
    //그러나 password를 가로채기합니다.
    //해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
    //같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있습니다.

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private  final PrincipalOauth2UserService principalOauth2UserService;
//    private  final JwtTokenProvider jwtTokenProvider;

    private final CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(new CustomFilter(), BasicAuthenticationFilter.class);
        http.csrf().disable();
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//세션 사용 안함
        .and()
                .addFilter(corsFilter)//인증(O), security Filter에 등록 / @CrossOrigin(인증X)
                .formLogin().disable()//FormLogin 안합
                .httpBasic().disable()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))//차단한 formLogin 대신 필터를 넣어준다. AuthenticationManager 가 필요
                .authorizeRequests()
                .antMatchers("/v1/api/member/**")//여기에 ROLE_USER로만 들어갈 수 있는 URL 주소
                .access("hasRole('USER')")
                .anyRequest()//그 외 모든 요청에 대해서 허용
                .permitAll();



//                http
//                    .httpBasic().disable()
//                    .csrf().disable()
//                     .formLogin().disable()
//                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                    .authorizeRequests()
//                    .antMatchers("/singin","/signup","/").permitAll()
//                    .anyRequest().hasRole("ROLE")
//                .and()
//                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
//


    }

    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/user/**").authenticated()
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//                .anyRequest().permitAll()
//            .and()
//                .formLogin()
//                .loginPage("/loginForm")
//                .loginProcessingUrl("/login")// /login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행
//                .defaultSuccessUrl("/")
//            .and()
//                .logout()
//                .logoutSuccessUrl("/loginForm")
//            .and()
//                .oauth2Login()
//                .loginPage("/login")
//                //구글 로그인이 완료된 뒤의 후처리가 필요(코드x,(엑세스토큰+사용자프로필 정보O)
//                .userInfoEndpoint()
//                .userService(principalOauth2UserService);
//
//
//
//    }


}
