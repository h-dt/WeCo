package com.dreamteam.hola.dto;

import com.dreamteam.hola.domain.Role;
import com.dreamteam.hola.domain.SocialType;
import com.dreamteam.hola.util.fileUpload.UploadFile;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class MemberDto {
    private Long memberId;
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
    private String withdrawalYn;
    private LocalDate withdrawalDate;
    private String socialType;//카카오,깃허브,구글


}
