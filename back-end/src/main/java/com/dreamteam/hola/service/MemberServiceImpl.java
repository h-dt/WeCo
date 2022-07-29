package com.dreamteam.hola.service;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.config.auth.PrincipalDetailsService;
import com.dreamteam.hola.dao.MemberMapper;
import com.dreamteam.hola.domain.Role;
import com.dreamteam.hola.dto.MemberDto;
import com.dreamteam.hola.util.jwt.JwtTokenProvider;
import com.dreamteam.hola.util.jwt.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
//    private final BCryptPasswordEncoder encoder;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PrincipalDetailsService principalDetailsService;

    @Override
    @Transactional
    public boolean signup(MemberDto memberDto) {
        memberDto.setRole(Role.ROLE_USER);
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        try {
            return memberMapper.signup(memberDto) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public Token signin(MemberDto memberDto) {
        PrincipalDetails findMember = (PrincipalDetails) principalDetailsService.loadUserByUsername(memberDto.getNickname());
        if (!passwordEncoder.matches(memberDto.getPassword(), findMember.getPassword())) {
            return new Token("access token create fail", "refresh token create fail");
        }
        return jwtTokenProvider.createtoken(findMember.getUsername(), findMember.getRole());
    }
}
