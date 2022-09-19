package com.dreamteam.hola.controller;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.dto.member.MemberInfoDto;
import com.dreamteam.hola.dto.member.MemberLoginDto;
import com.dreamteam.hola.dto.member.MemberSignupDto;
import com.dreamteam.hola.dto.member.MemberUpdateDto;
import com.dreamteam.hola.exception.ErrorResponse;
import com.dreamteam.hola.service.MemberService;
import com.dreamteam.hola.util.jwt.Token;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberServiceImpl;

    /*********************************** GET API ***********************************/

    @Operation(summary = "로그인 하고 있는 Member 정보 가져오는 API", description = "로그인 하고 있는 Member 정보를 가져옵니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 한 사용자 정보", content = @Content(schema = @Schema(implementation = MemberInfoDto.class))),
    })
    @GetMapping("/member")
    public ResponseEntity<?> getLoginMember(@Parameter(hidden = true)
                                                @AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.info("GET /member API Called");
        Long memberId = getMemberId(principalDetails);
        return new ResponseEntity<>(memberServiceImpl.getLoginMember(memberId), HttpStatus.OK);
    }

    /*********************************** POST API ***********************************/

    @Operation(summary = "로그인 API", description = "email과 password를 통해서 로그인합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "access token과 refresh token ", content = @Content(schema = @Schema(implementation = Token.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 회원입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "비밀번호가 올바르지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "이메일 형식이 올바르지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "토큰 확인 중 알 수 없는 error가 발생하였습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "잘못된 타입입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "만료된 토큰입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "지원하지 않는 토큰 형식입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "권한이 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Parameter(content = @Content(schema = @Schema(implementation = MemberLoginDto.class)))
                                        @Validated @RequestBody MemberLoginDto memberDto) {
        log.info("Get /signin API Called");
        Token result = memberServiceImpl.signin(memberDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "회원가입 API", description = "email, password, nickname으로 회원가입합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "이메일 형식이 올바르지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "해당 이메일은 이미 존재합니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping("/member")
    public ResponseEntity<?> signup(@Parameter(content = @Content(schema = @Schema(implementation = MemberSignupDto.class)))
                                    @Validated @RequestBody MemberSignupDto requestDto) throws IOException {
        log.info("Post /member API Called");
        memberServiceImpl.signup(requestDto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /*********************************** PUT API ***********************************/

    @Operation(summary = "Member 프로필 변경 API", description = "로그인 한 사용자의 프로필을 변경합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "빈 파일명으론 프로필을 등록할 수 없습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "해당 확장자는 지원하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "프로필 수정 중 오류가 발생하였습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PutMapping(value = "/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProfile(@Parameter(hidden = true)
                                               @AuthenticationPrincipal PrincipalDetails principalDetails,
                                           @Parameter(description = "변경할 프로필(MultipartFile type)", required = true)
                                               @RequestPart(name = "profile", required = false) MultipartFile profile) throws IOException {
        log.info("Put /profile API Called");
//        if(profile.isEmpty())
//            throw new NullPointerException();
        Long memberId = getMemberId(principalDetails);
        memberServiceImpl.updateProfile(memberId, profile);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @Operation(summary = "Member nickname 변경 API", description = "사용자 nickname을 memberUpdateDto에 있는 nickname으로 변경합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "{nickname=닉네임은 특수문자를 제외한 2~10자리여야 합니다.}", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PutMapping("/member")
    public ResponseEntity<?> update(@Parameter(hidden = true)
                                    @AuthenticationPrincipal PrincipalDetails principalDetails,
                                    @Parameter(content = @Content(schema = @Schema(implementation = MemberUpdateDto.class)))
                                    @Validated @RequestBody MemberUpdateDto memberDto) {
        log.info("Put /member API Called");
        Long memberId = getMemberId(principalDetails);
        memberServiceImpl.update(memberId, memberDto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /*********************************** DELETE API ***********************************/


    @Operation(summary = "Member 삭제 API", description = "로그인 한 사용자의 Id를 기준으로 회원 상태를 비활성화로 바꿉니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
    })
    @DeleteMapping("/member")
    public ResponseEntity<?> withDrawal(@Parameter(hidden = true)
                                            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.info("DELETE /member API Called");
        Long memberId = getMemberId(principalDetails);
        memberServiceImpl.delete(memberId);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /**
     * 로그인한 사용자 Id를 추출하는 Method
     *
     * @param principalDetails 로그인한 사용자 정보 객체
     * @return Long 로그인한 사용자의 Id
     */
    private Long getMemberId(PrincipalDetails principalDetails) {
        return principalDetails.getMemberDto().getMemberId();
    }
}