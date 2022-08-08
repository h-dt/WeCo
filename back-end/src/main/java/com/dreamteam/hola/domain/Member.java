package com.dreamteam.hola.domain;

import com.dreamteam.hola.dto.MemberDto;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member{
    private Long memberId;
    private String username;
    private String nickname;
    private String password;
    private String email;
    private String profileImage;
    private Role role;
    private LocalDate regDate;
    private LocalDate modDate;
    private char withdrawalYn;
    private LocalDate withdrawalDate;
    private String socialType;//카카오,깃허브,구글


    public MemberDto toDto(){
        if (password == null || password.isEmpty()) {
            password = UUID.randomUUID().toString();
        }
        if (role == null) {
            role = Role.ROLE_USER;
        }
        return MemberDto.builder()
                .email(email)
                .nickname(nickname)
                .profileImage(profileImage)
                .password(password)
                .role(role)
                .socialType(socialType)
                .build();
    }
}
