package com.dreamteam.hola.controller;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.dto.BoardDto;
import com.dreamteam.hola.dto.MemberDto;
import com.dreamteam.hola.exception.ErrorResponse;
import com.dreamteam.hola.service.BoardService;
import com.dreamteam.hola.service.BoardServiceImpl;
import com.dreamteam.hola.service.HeartServiceImpl;
import com.dreamteam.hola.service.MemberService;
import com.dreamteam.hola.util.jwt.Token;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Log4j2
@RequiredArgsConstructor
@Api
public class MemberController {
    private final MemberService memberServiceImpl;
    private final HeartServiceImpl heartServiceImpl;

    @ApiOperation(value = "로그인",notes = "사용자 로그인")
    @PostMapping("/signin")
    public ResponseEntity<?> signin(
            @RequestBody MemberDto memberDto) {
        Token result = memberServiceImpl.signin(memberDto);
        if(result.getAccessToken().equals("access token create fail"))
            return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
        else
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "회원가입",notes = "신규 사용자를 생성합니다.")
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Validated @RequestPart("key") MemberDto memberDto, @RequestPart(value = "file",required = false)MultipartFile multipartFile) throws IOException {

        return new ResponseEntity<>(memberServiceImpl.signup(memberDto,multipartFile),HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal PrincipalDetails principalDetails){
        Long id = principalDetails.getMemberDto().getMemberId();
        log.info(" 회원가져오기 id={}",id);
        MemberDto result = memberServiceImpl.getProfile(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PutMapping("/member/{id}")
    public ResponseEntity<?> updateMember(@PathVariable("id")Long id,
                                          @Validated @RequestPart("key")MemberDto memberDto,
                                          @RequestPart(value = "file",required = false)MultipartFile multipartFile
                                         ) throws IOException {
        memberServiceImpl.update(id,memberDto,multipartFile);
        return  new ResponseEntity<>("success",HttpStatus.OK);
    }
    @GetMapping("/myheart")
    public ResponseEntity<?> showMyHeart (@AuthenticationPrincipal PrincipalDetails principalDetails){
        log.info("id={}",principalDetails.getMemberDto().getMemberId());
        Long memberId = principalDetails.getMemberDto().getMemberId();
        return new ResponseEntity<>(heartServiceImpl.HeartList(memberId),HttpStatus.OK);

    }


}