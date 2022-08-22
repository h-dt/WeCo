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

    @ApiOperation(value = "로그인 API",notes = "사용자 로그인")
    @ApiImplicitParam(name = "MemberLoginDto", value = "로그인에 필요한 정보를 담은 Dto")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Api 요청 시 사용되는 access token 및 refresh token", response = Token.class),
            @ApiResponse(code = 400, message = "{email=이메일 형식이 올바르지 않습니다., password=비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.}", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "토큰 확인 중 알 수 없는 error가 발생하였습니다.", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "잘못된 타입입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "만료된 토큰입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "지원하지 않는 토큰 형식입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "권한이 없습니다.", response = ErrorResponse.class)
    })
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Validated @RequestBody MemberLoginDto memberDto) {
        Token result = memberServiceImpl.signin(memberDto);
        if(result.getAccessToken().equals("access token create fail"))
            return new ResponseEntity<>(result, HttpStatus.FORBIDDEN);
        else
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "회원가입",notes = "신규 사용자를 생성합니다.")
    @ApiImplicitParam(name = "MemberDto", value = "회원가입에 필요한 정보를 담은 Dto")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "{nickname=닉네임은 특수문자를 제외한 2~10자리여야 합니다., email=이메일 형식이 올바르지 않습니다., password=비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.}", response = ErrorResponse.class),
            @ApiResponse(code = 409, message = "중복되는 아이디 입니다.", response = ErrorResponse.class),
    })
    @PostMapping("/member")
    public ResponseEntity<?> signup(@Validated @RequestBody MemberDto memberDto) throws IOException {
        memberServiceImpl.signup(memberDto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @ApiOperation(value = "사용자 프로필 수정",notes = "로그인 한 사용자의 프로필을 수정합니다.")
    @ApiImplicitParam(name = "MemberUpdateDto", value = "변경할 nickname이 담긴 Dto")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "빈 파일명으론 프로필을 등록할 수 없습니다.", response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "해당 확장자는 지원하지 않습니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "프로필 수정 중 오류가 발생하였습니다.", response = ErrorResponse.class),
    })
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails, MultipartFile profile) throws IOException {
        Long id = principalDetails.getMemberDto().getMemberId();
        memberServiceImpl.updateProfile(id, profile);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @ApiOperation(value = "사용자 정보 수정",notes = "로그인 한 사용자의 정보를 수정합니다.")
    @ApiImplicitParam(name = "MemberUpdateDto", value = "변경할 nickname이 담긴 Dto(현재는 nickname만 변경 가능)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "{nickname=닉네임은 특수문자를 제외한 2~10자리여야 합니다.}", response = ErrorResponse.class),
    })
    @PutMapping("/member")
    public ResponseEntity<?> update(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails, @Validated @RequestBody MemberUpdateDto memberDto) throws IOException {
        Long id = principalDetails.getMemberDto().getMemberId();
        memberServiceImpl.update(id, memberDto);
        return  new ResponseEntity<>("OK",HttpStatus.OK);
    }

    @ApiOperation(value = "사용자 삭제(회원 탈퇴)",notes = "로그인 한 사용자를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    @DeleteMapping("/member")
    public ResponseEntity<?>  withDrawal(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails){
        Long memberId = principalDetails.getMemberDto().getMemberId();
        memberServiceImpl.delete(memberId);
        return  new ResponseEntity<>("OK",HttpStatus.OK);
    }
}