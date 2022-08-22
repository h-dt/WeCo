package com.dreamteam.hola.service;

import com.dreamteam.hola.dto.member.MemberDto;
import com.dreamteam.hola.dto.member.MemberLoginDto;
import com.dreamteam.hola.dto.member.MemberUpdateDto;
import com.dreamteam.hola.util.jwt.Token;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MemberService {

    boolean signup(MemberDto memberDto) throws IOException;

    Token signin(MemberLoginDto memberDto);

    boolean updateProfile(Long memberid, MultipartFile profile) throws IOException;

    boolean update(Long id, MemberUpdateDto memberDto);

    boolean delete(Long id);
}
