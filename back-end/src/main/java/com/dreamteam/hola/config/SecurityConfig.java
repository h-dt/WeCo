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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration//빈등록(IOC)관리
@EnableWebSecurity//시큐리티 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled = true)//특정 주소로 접근을 하면 권한 및 인증을 미리 체크
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


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
        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService().passwordEncoder(encoderPWD());
    }
}
