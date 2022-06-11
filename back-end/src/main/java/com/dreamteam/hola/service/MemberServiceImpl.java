package com.dreamteam.hola.service;

import com.dreamteam.hola.dao.MemberMapper;
import com.dreamteam.hola.domain.Member;
import com.dreamteam.hola.domain.Role;
import com.dreamteam.hola.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.Loader;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder encoder;


    @Override
    @Transactional
    public void joinMember(MemberDto memberDto) {
        memberDto.setPassword(encoder.encode(memberDto.getPassword()));
        memberDto.setRole(Role.ROLE_USER);
        memberMapper.joinMember(memberDto);
    }

}
