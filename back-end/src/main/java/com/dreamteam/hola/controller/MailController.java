package com.dreamteam.hola.controller;

import com.dreamteam.hola.dto.MailDto;
import com.dreamteam.hola.exception.ErrorResponse;
import com.dreamteam.hola.service.MailServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class MailController {
    private final MailServiceImpl mailServiceImpl;

    @ApiOperation(value = "사이트 평가 API", notes = "사용자(익명)가 사이트를 평가한 내용을 보내줍니다.")
    @ApiImplicitParam(name = "MailDto", value = "사용자가 평가한 내용을 담는 Request Body")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "올바르지 않은 매개변수 형식입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "메일 발송 도중 error가 발생하였습니다.", response = ErrorResponse.class),
    })
    @PostMapping("/mail")
    public ResponseEntity<?> mail(@Valid @RequestBody MailDto mailDto) throws MessagingException {
        mailServiceImpl.sendMail(mailDto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
