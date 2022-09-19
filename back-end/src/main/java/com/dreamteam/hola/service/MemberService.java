package com.dreamteam.hola.service;

import com.dreamteam.hola.dto.member.MemberInfoDto;
import com.dreamteam.hola.dto.member.MemberLoginDto;
import com.dreamteam.hola.dto.member.MemberSignupDto;
import com.dreamteam.hola.dto.member.MemberUpdateDto;
import com.dreamteam.hola.util.jwt.Token;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface MemberService {

    void signup(MemberSignupDto requestDto) throws IOException;

    Token signin(MemberLoginDto memberDto);

    Long updateProfile(Long memberid, MultipartFile profile) throws IOException;

    Long update(Long id, MemberUpdateDto memberDto);

    Long delete(Long id);

    MemberInfoDto getLoginMember(Long id);
}
