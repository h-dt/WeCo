package com.dreamteam.hola.controller;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.config.auth.PrincipalDetailsService;
import com.dreamteam.hola.dto.MemberDto;
import com.dreamteam.hola.service.MemberService;
import com.dreamteam.hola.util.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
public class MemberController {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PrincipalDetailsService principalDetailsService;
    private final MemberService memberServiceImpl;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody MemberDto memberDto) {
        PrincipalDetails findMember = (PrincipalDetails) principalDetailsService.loadUserByUsername(memberDto.getUsername());
        if (!passwordEncoder.matches(memberDto.getPassword(), findMember.getPassword())) {
            return new ResponseEntity<>("fail", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(jwtTokenProvider.createtoken(findMember.getUsername(), findMember.getRole()), HttpStatus.ACCEPTED);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody MemberDto memberDto) {
        if(memberServiceImpl.signup(memberDto))
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
