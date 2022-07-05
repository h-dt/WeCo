package com.dreamteam.hola.config;

import com.dreamteam.hola.util.jwt.JwtAuthenticationFilter;
import com.dreamteam.hola.util.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

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
                .httpBasic().disable()  // 비인증시 로그인폼 화면으로 리다이렉트 되는 기능 제거(rest api 이므로)
                .csrf().disable()   // rest api이므로 csrf 보안 필요없음
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // token 방식 처리 이므로 세션 필요없음
                .and()
                .authorizeRequests()
                .antMatchers("/signin", "/signup").permitAll()
                .anyRequest().hasRole("USER")
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);    // jwt token 필터를 id/password 인증 필터 전에 넣기
    }
}
