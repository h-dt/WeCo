package com.dreamteam.hola.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControlAdvice {

    @ExceptionHandler({NullPointerException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handlerException1(){
        return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR,"존재하지 않는 데이터 입니다.");
    }

}
