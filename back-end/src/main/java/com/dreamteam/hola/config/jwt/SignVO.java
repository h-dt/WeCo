package com.dreamteam.hola.config.jwt;

import lombok.Data;

@Data
public class SignVO {//로그인이 성공하면 토큰을 담아보내거나 , 실패하면 메세지를 담아 보내기 위한 클래스
    private String result,message,token;
}
