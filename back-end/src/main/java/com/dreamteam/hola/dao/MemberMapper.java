package com.dreamteam.hola.dao;

import com.dreamteam.hola.domain.Member;
import com.dreamteam.hola.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberMapper {

    void joinMember(Member member);

    Member findByUsername(String username);
}
