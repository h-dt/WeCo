package com.dreamteam.hola.exception.board;

import com.dreamteam.hola.controller.BoardController;
import com.dreamteam.hola.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = BoardController.class)
public class BoardControlAdvice {

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse BoardException(){
        return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR,"존재하지 않는 글입니다.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse BoardBadRequestException() {
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, "올바르지 않은 매개변수 형식입니다.");
    }

    @ExceptionHandler(BoardAuthorizationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse BoardAuthorizationException(){
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, "해당 게시글에 권한이 없습니다.");
    }
    // 이러한 방식으로 가능
    /**
     * javax.validation.Valid or @Validated 으로 binding error 발생 시 발생
     * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할 경우 발생
     */
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        log.error("MethodArgumentNotValidException : " + e.getMessage());
//        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }

}
