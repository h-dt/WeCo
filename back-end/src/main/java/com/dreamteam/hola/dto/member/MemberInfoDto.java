package com.dreamteam.hola.dto.member;

import com.dreamteam.hola.domain.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class MemberInfoDto {

    private Long memberId;

    private String nickname;

    private String password;

    private String email;

    private String profileImage;

    private Role role;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Seoul")
    private LocalDateTime regDate;
}
