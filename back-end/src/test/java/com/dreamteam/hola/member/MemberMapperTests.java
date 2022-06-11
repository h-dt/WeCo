package com.dreamteam.hola.member;

import com.dreamteam.hola.dao.MemberMapper;
import com.dreamteam.hola.domain.Member;
import com.dreamteam.hola.dto.MemberDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberMapperTests {

    @Autowired
    private MemberMapper mapper;

    @Test
    @DisplayName("username 으로 회원단건조회")
    void findByUsername(){
        Member member = mapper.findByUsername("testID");
        try{
            String memberJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(member);
            System.out.println("=================");
            System.out.println(memberJson);
            System.out.println("=================");
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }
}
