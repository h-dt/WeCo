package com.dreamteam.hola.config;

import com.dreamteam.hola.util.jwt.JwtAccessDeniedHandler;
import com.dreamteam.hola.util.jwt.JwtAuthenticationEntryPoint;
import com.dreamteam.hola.util.jwt.JwtAuthenticationFilter;
import com.dreamteam.hola.util.jwt.JwtTokenProvider;
import com.dreamteam.hola.util.oauth2.CustomOAuth2UserService;
import com.dreamteam.hola.util.oauth2.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomOAuth2UserService oAuth2UserService;
    private final OAuth2SuccessHandler successHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable() // 비인증시 로그인폼 화면으로 리다이렉트 되는 기능 제거(rest api 이므로)
                .csrf().disable() // rest api이므로 csrf 보안 필요없음
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // token 방식 처리 이므로 세션 필요없음
                .and()
                .authorizeRequests()
                .antMatchers("/signin", "/", "/boards").permitAll()
                .antMatchers("/images/**, /js/**").permitAll()
                .antMatchers(HttpMethod.POST, "/member/**").permitAll()
                .antMatchers(HttpMethod.GET, "/board/**").permitAll()
                .antMatchers(HttpMethod.GET, "/comment/**").permitAll()
                .anyRequest().hasRole("USER")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .oauth2Login() // oauth2 Login 설정 시작
                .successHandler(successHandler) // 로그인 성공 시, handler 설정
                .userInfoEndpoint().userService(oAuth2UserService); // oauth2 성공 후 설정 시작 + oAuth2UserService 에서 서버에서 가져온 사용자 정보를 처리


        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // jwt token 필터를 id/password 인증 필터 전에 넣기
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/swagger-resources/**","/swagger-ui.html/**", "/v2/api-docs", "/webjars/**");
    }

}