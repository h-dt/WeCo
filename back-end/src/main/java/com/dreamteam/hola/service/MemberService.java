package com.dreamteam.hola.service;

import com.dreamteam.hola.dto.MemberDto;
import com.dreamteam.hola.util.jwt.Token;

public interface MemberService {
    boolean signup(MemberDto memberDto);

    Token signin(MemberDto memberDto);
}
