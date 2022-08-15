package com.dreamteam.hola.util.jwt;

import lombok.Getter;

@Getter
public enum ExceptionCode {

    UNKNOWN_ERROR("401","토큰 확인 중 알 수 없는 error가 발생하였습니다."),
    WRONG_TYPE_TOKEN("401", "잘못된 타입입니다."),
    EXPIRED_TOKEN("401", "만료된 토큰입니다."),
    UNSUPPORTED_TOKEN("401", "지원하지 않는 토큰 형식입니다."),
    ACCESS_DENIED("401", "잘못된 입력입니다."),
    PERMISSION_DENIED("403", "권한이 없습니다.");

    private final String code;
    private final String message;

    ExceptionCode(final String code, final String message){
        this.code = code;
        this.message = message;
    }
}
