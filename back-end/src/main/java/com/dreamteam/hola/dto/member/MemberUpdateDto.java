package com.dreamteam.hola.dto.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberUpdateDto {

    @Schema(hidden = true)
    @JsonIgnore
    private Long id;

    @Schema(description = "특수문자를 제외한 2~10자리의 변경 할 nickname", example = "weco", required = true)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣A-Za-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    @NotBlank
    private String nickname;
}
