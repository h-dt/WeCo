package com.dreamteam.hola.controller;

import com.dreamteam.hola.dto.MemberDto;
import com.dreamteam.hola.service.MemberService;
import com.dreamteam.hola.util.jwt.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Log4j2
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberServiceImpl;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody MemberDto memberDto) {
        Token result = memberServiceImpl.signin(memberDto);
        if(result.getAccessToken().equals("access token create fail"))
            return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
        else
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestPart("key") MemberDto memberDto, @RequestPart(value = "file",required = false)MultipartFile multipartFile) throws IOException {

        if(memberServiceImpl.signup(memberDto,multipartFile))
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}