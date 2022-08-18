package com.dreamteam.hola.dto.member;

import com.dreamteam.hola.domain.Role;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@ApiModel
public class MemberDto {

    private Long memberId;

    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    private String nickname;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    @ApiModelProperty(value = "로그인 password",example = "1234")
    private String password;

    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank
    @ApiModelProperty(value = "로그인 email", example = "testEmail@naver.com")
    private String email;
    private String profileImage;
    private Role role;
    @ApiModelProperty(value = "ㄷㅌ")
    private LocalDate regDate;
    private LocalDate modDate;
    private String withdrawalYn;
    private LocalDate withdrawalDate;
    private String socialType;//카카오,깃허브,구글


}
