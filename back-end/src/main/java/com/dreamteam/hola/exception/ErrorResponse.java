package com.dreamteam.hola.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private int code;
    private String message;

    public static ErrorResponse of (HttpStatus httpStatus, String message ) {
        return new ErrorResponse(httpStatus.value(), message);
    }
}
