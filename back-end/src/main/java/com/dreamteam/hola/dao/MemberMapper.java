package com.dreamteam.hola.dao;

import com.dreamteam.hola.dto.member.MemberInfoDto;
import com.dreamteam.hola.dto.member.MemberSignupDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    MemberInfoDto findByEmail(String email);

    Long signup(MemberSignupDto reqeustDto);

    MemberInfoDto findById(Long id);

    Long update(MemberInfoDto memberDto);

    Long delete(Long id);

    Long updateProfile(MemberInfoDto memberDto);
}