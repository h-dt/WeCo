package com.dreamteam.hola.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    private Long memberNo;
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
}
