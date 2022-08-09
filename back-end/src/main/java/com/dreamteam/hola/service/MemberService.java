package com.dreamteam.hola.service;

import com.dreamteam.hola.domain.Member;
import com.dreamteam.hola.dto.BoardDto;
import com.dreamteam.hola.dto.MemberDto;
import com.dreamteam.hola.util.jwt.Token;

public interface MemberService {

    boolean signup(MemberDto memberDto, MultipartFile multipartFile) throws IOException;

    Token signin(MemberDto memberDto);

    MemberDto getProfile(Long id);
    void update(Long id,MemberDto memberDto,MultipartFile multipartFile) throws IOException;




}
