package com.dreamteam.hola.controller;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.dto.member.MemberDto;
import com.dreamteam.hola.dto.member.MemberLoginDto;
import com.dreamteam.hola.exception.ErrorResponse;
import com.dreamteam.hola.service.HeartServiceImpl;
import com.dreamteam.hola.service.MemberService;
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

import java.io.IOException;

@RestController
@Log4j2
@RequiredArgsConstructor
@Api
public class MemberController {
    private final MemberService memberServiceImpl;
    private final HeartServiceImpl heartServiceImpl;

    @ApiOperation(value = "로그인 API",notes = "사용자 로그인")
    @ApiImplicitParam(name = "MemberLoginDto",value = "로그인에 필요한 email과 password를 담은 RequestBody",required = true)
    @ApiResponses({
            @ApiResponse(code=200, message ="OK", response = Token.class),
            @ApiResponse(code=401, message = "회원이 아닙니다.",response = ErrorResponse.class),
            @ApiResponse(code=400, message = "잘못된 형식으로 요청하였습니다.",response = ErrorResponse.class)
    })
    @PostMapping("/signin")
    public ResponseEntity<?> signin(
            @RequestBody MemberLoginDto memberDto) {
        Token result = memberServiceImpl.signin(memberDto);
        if(result.getAccessToken().equals("access token create fail"))
            return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
        else
            return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "회원가입 API",notes = "신규 사용자를 생성합니다.")
    @ApiImplicitParam(name = "memberDto",value = "회원가입에 필요한 email,password,nickname,profileImage를 담은 RequestBody",required = true,dataType = "MemberDto",paramType = "body")
    @PostMapping("/signup")

    public ResponseEntity<?> signup(@Validated @RequestPart MemberDto memberDto,
                                    @RequestPart(value = "file",required = false)MultipartFile multipartFile) throws IOException {

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
    @DeleteMapping("/member")
    public ResponseEntity<?>  withDrawal(@AuthenticationPrincipal PrincipalDetails principalDetails){
        Long memberId = principalDetails.getMemberDto().getMemberId();
        boolean result = memberServiceImpl.delete(memberId);

        return  result? new ResponseEntity<>(result,HttpStatus.OK) : new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}