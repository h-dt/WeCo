package com.dreamteam.hola.exception.member;

import com.dreamteam.hola.controller.MemberController;
import com.dreamteam.hola.exception.ErrorResponse;
import com.dreamteam.hola.exception.file.FileNameNullException;
import com.dreamteam.hola.exception.file.NotAllowExtensionException;
import com.dreamteam.hola.exception.file.NotExistException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

        LinkedHashMap<String, String> errorList = new LinkedHashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errorList.put(fieldName,errorMessage);
        });
        return ErrorResponse.of(HttpStatus.BAD_REQUEST,errorList.toString());

    }
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ErrorResponse memberHandlerException3(NullPointerException ex){
        return ErrorResponse.of(HttpStatus.UNAUTHORIZED,"인증되지 않는 회원입니다.");
    }

    @ExceptionHandler(FileNameNullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleFileNameNullException(FileNameNullException fnn){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, fnn.getMessage());
    }

    @ExceptionHandler(NotAllowExtensionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleNotAllowExtenstionException(NotAllowExtensionException nae){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, nae.getMessage());
    }

    @ExceptionHandler(NotExistException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handleNotExistException(NotExistException nee){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, nee.getMessage());
    }
}
