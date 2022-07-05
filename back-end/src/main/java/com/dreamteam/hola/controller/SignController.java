package com.dreamteam.hola.controller;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.config.auth.PrincipalDetailsService;
//import com.dreamteam.hola.config.jwt.JwtTokenProvider;
import com.dreamteam.hola.domain.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class SignController {

//    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/token")
    public String postToken() {
        return "<h1>Success</h1>";
    }
}

