package com.dreamteam.hola.exception;

import com.dreamteam.hola.controller.MailController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.mail.AuthenticationFailedException;

@RestControllerAdvice(assignableTypes = MailController.class)
public class MailControlAdvice {

    @ExceptionHandler(AuthenticationFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse mailErrorException() {
        return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, "메일 발송 도중 error가 발생하였습니다.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handlerException2() {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, "올바르지 않은 매개변수 형식입니다.");
    }
}
