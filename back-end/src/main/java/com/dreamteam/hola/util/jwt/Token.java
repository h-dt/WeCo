package com.dreamteam.hola.util.jwt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class Token {
    @ApiModelProperty(value = "accessToken",example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0RW1haWxAbmF2ZXIuY29tIiwicm9sZSI6IlJPTEVfVVNFUiIsImlhdCI6MTY2MDU2MTQ5OCwiZXhwIjoxNjYwNTY1MDk4fQ.GcimL0xCeuf-RlgdeYzac9EXWNIv74kb3b37ETqFxgc")
    private String accessToken;
    @ApiModelProperty(value = "refreshToken",example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0RW1haWxAbmF2ZXIuY29tIiwicm9sZSI6IlJPTEVfVVNFUiIsImlhdCI6MTY2MDU2MTQ5OCwiZXhwIjoxNjY4MzM3NDk4fQ.t6sUjVkiy_aKjGTh5xQqATKV7OqxK-8T0oV-He1toOg")
    private String refreshToken;

    public Token(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}