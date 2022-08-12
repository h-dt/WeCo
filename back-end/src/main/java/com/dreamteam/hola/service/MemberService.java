package com.dreamteam.hola.service;

import com.dreamteam.hola.dto.member.MemberDto;
import com.dreamteam.hola.dto.member.MemberLoginDto;
import com.dreamteam.hola.dto.member.MemberReqDto;
import com.dreamteam.hola.util.jwt.Token;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MemberService {

    boolean signup(MemberDto memberDto, MultipartFile multipartFile) throws IOException;

    Token signin(MemberLoginDto memberDto);

    MemberDto getProfile(Long id);
    void update(Long id,MemberDto memberDto,MultipartFile multipartFile) throws IOException;




}
