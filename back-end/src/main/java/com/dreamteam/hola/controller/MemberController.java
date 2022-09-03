package com.dreamteam.hola.controller;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.dto.member.MemberDto;
import com.dreamteam.hola.dto.member.MemberLoginDto;
import com.dreamteam.hola.dto.member.MemberUpdateDto;
import com.dreamteam.hola.exception.ErrorResponse;
import com.dreamteam.hola.service.HeartServiceImpl;
import com.dreamteam.hola.service.MemberService;
import com.dreamteam.hola.service.S3FileUploadService;
import com.dreamteam.hola.util.jwt.Token;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;

@RestController
@Log4j2
@RequiredArgsConstructor
@Api
public class MemberController {
    private final MemberService memberServiceImpl;
    private final HeartServiceImpl heartServiceImpl;
    private final S3FileUploadService s3FileUploadService;


    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Validated @RequestBody MemberLoginDto memberDto) {
        Token result = memberServiceImpl.signin(memberDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/member")
    public ResponseEntity<?> signup(@Validated @RequestBody MemberDto memberDto) throws IOException {
        memberServiceImpl.signup(memberDto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }


    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails, MultipartFile profile) throws IOException {
        Long id = principalDetails.getMemberDto().getMemberId();
        memberServiceImpl.updateProfile(id, profile);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }


    @PutMapping("/member")
    public ResponseEntity<?> update(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails, @Validated @RequestBody MemberUpdateDto memberDto) throws IOException {
        Long id = principalDetails.getMemberDto().getMemberId();
        memberServiceImpl.update(id, memberDto);
        return  new ResponseEntity<>("OK",HttpStatus.OK);
    }


    @DeleteMapping("/member")
    public ResponseEntity<?>  withDrawal(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails){
        Long memberId = principalDetails.getMemberDto().getMemberId();
        memberServiceImpl.delete(memberId);
        return  new ResponseEntity<>("OK",HttpStatus.OK);
    }


    @GetMapping("/member")
    public ResponseEntity<?> getLoginMember(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long memberId = principalDetails.getMemberDto().getMemberId();
        return new ResponseEntity<>(memberServiceImpl.getLoginMember(memberId),HttpStatus.OK);
    }
}