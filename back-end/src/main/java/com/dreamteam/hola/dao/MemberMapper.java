package com.dreamteam.hola.dao;

import com.dreamteam.hola.domain.Member;
import com.dreamteam.hola.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {


    MemberDto findByNickname(String nickname);

    MemberDto findByEmail(String email);

    int signup(MemberDto memberDto);

    MemberDto findById(Long id);

    int update(MemberDto memberDto);


}
