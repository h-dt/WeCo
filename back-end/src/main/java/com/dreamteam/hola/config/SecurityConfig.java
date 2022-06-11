package com.dreamteam.hola.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity//시큐리티 필터가 등록이 된다.(내부에 @Configure 내장)
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)//@secured 어노테이션 활성화,@preAuthorize 활성화
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //시큐리티가 대신 로그인을 해줌.
    //그러나 password를 가로채기합니다.
    //해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
    //같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있습니다.
    @Bean
    public BCryptPasswordEncoder encoderPWD(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
            .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")// /login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행
                .defaultSuccessUrl("/");



    }


}
