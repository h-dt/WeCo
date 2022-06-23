package com.dreamteam.hola.dto;

import com.dreamteam.hola.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private Long memberNo;
    private String username;
    private String nickname;
    private String password;
//    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
//    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;
    private String profileImage;
    private Role role;
    private LocalDate regDate;
    private LocalDate modDate;
    private char withdrawalYn;
    private LocalDate withdrawalDate;
    private String socialType;//카카오,깃허브,구글
}
