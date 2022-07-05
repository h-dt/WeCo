package com.dreamteam.hola.service;

import com.dreamteam.hola.config.auth.PrincipalDetailsService;
import com.dreamteam.hola.dao.MemberMapper;
import com.dreamteam.hola.domain.Member;
import com.dreamteam.hola.domain.Role;
import com.dreamteam.hola.dto.MemberDto;
import com.dreamteam.hola.util.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder encoder;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PrincipalDetailsService principalDetailsService;


    @Override
    @Transactional
    public void joinMember(Member member) {
        member.setPassword(encoder.encode(member.getPassword()));
        member.setRole(Role.ROLE_USER);
        memberMapper.joinMember(member);
    }

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

}
