package com.dreamteam.hola.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//임의로 @붙임
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {
    private String memberId;
    private String nickname;
    private String password;
    private String email;
    private String profile_image;
    private LocalDate reg_date;
    private LocalDate mod_date;
    private char withdrawal_yn;
    private LocalDate withdrawal_date;
    private String social_type;
}
