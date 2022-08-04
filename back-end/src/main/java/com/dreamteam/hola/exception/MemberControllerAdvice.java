package com.dreamteam.hola.exception;

import com.dreamteam.hola.controller.MemberController;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.LinkedHashMap;


@RestControllerAdvice(assignableTypes = MemberController.class)
public class MemberControllerAdvice {

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ErrorResponse memberHandlerException1(){
        return ErrorResponse.of(HttpStatus.CONFLICT,"중복되는 아이디 입니다.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse memberHandlerException2(MethodArgumentNotValidException ex){

        LinkedHashMap<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return ErrorResponse.of(HttpStatus.BAD_REQUEST,errors.toString());

    }






}
