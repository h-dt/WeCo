package com.dreamteam.hola.exception;

import com.dreamteam.hola.controller.CommentController;
import com.dreamteam.hola.exception.comment.BoardNotFoundException;
import com.dreamteam.hola.exception.comment.CommentNotMatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = CommentController.class)
public class CommentControlAdvice {

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse CommentException(){
        return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR,"존재하지 않는 댓글입니다.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse CommentBadRequestException() {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, "올바르지 않은 매개변수 형식입니다.");
    }

    @ExceptionHandler(BoardNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleBoardNotFoundException(BoardNotFoundException bnf){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, bnf.getMessage());
    }

    @ExceptionHandler(CommentNotMatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleCommentNotMatchException(CommentNotMatchException cnm){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, cnm.getMessage());
    }
}
