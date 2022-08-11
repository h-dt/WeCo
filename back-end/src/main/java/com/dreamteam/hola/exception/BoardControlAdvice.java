package com.dreamteam.hola.exception;

import com.dreamteam.hola.controller.BoardController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = BoardController.class)
public class BoardControlAdvice {

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handlerException1(){
        return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR,"존재하지 않는 글입니다.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handlerException2() {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, "올바르지 않은 매개변수 형식입니다.");
    }
}
