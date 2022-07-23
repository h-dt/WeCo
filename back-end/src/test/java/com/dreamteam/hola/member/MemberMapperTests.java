package com.dreamteam.hola.member;

import com.dreamteam.hola.dao.MemberMapper;
import com.dreamteam.hola.domain.Member;
import com.dreamteam.hola.domain.Role;
import com.dreamteam.hola.dto.MemberDto;
import com.dreamteam.hola.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
public class MemberMapperTests {

    @Value("${file.upload.location}")
    private String fileDir;
    @Autowired
    private MemberMapper mapper;

    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("username 으로 회원단건조회")
    void findByUsername(){
        MemberDto memberDto = mapper.findByUsername("testmember3");
        try{
            String memberJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(memberDto);
            System.out.println("=================");
            System.out.println(memberJson);
            System.out.println("=================");
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }
//    @Test
//    @DisplayName("회원가입 테스트")
//    void testInsert () throws Exception{
//
//        MockMultipartFile file = new MockMultipartFile("file", "filename-1.jpeg", "image/jpeg", "some-image".getBytes());
//
//        MemberDto memberDto = new MemberDto;
//
//        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//        String imageFileName = uuid + "_" + memberDto.getProfileImage().getOriginalFilename();
//        Path imageFilePath =  Paths.get(fileDir + "/"+imageFileName);
//
//        //given
//        MemberDto member = MemberDto.builder()
//                .memberId(100L)
//                .username("jaeyeal")
//                .nickname("codej")
//                .password("1111")
//                .email("jyyoun1022@naver.com")
//                .profileImage()
//                .role(Role.ROLE_USER)
//                .regDate(LocalDate.now())
//                .modDate(null)
//                .withdrawalYn("N")
//                .withdrawalDate(null)
//                .socialType("local")
//                .build();
//
//        //when
//        int result = mapper.signup(member);
//        //then
//
//
//    }
//    private MockMultipartFile getMockMultipartFile(String fileName, String contentType, String path) throws  IOException {
//        FileInputStream fileInputStream = new FileInputStream(new File(path));
//        return new MockMultipartFile(fileName, fileName + "." + contentType, contentType, fileInputStream);
//    }

    }



