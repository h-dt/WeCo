package com.dreamteam.hola.controller;

import com.dreamteam.hola.dto.MailDto;
import com.dreamteam.hola.service.MailServiceImpl;
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


    @PostMapping("/mail")
    public ResponseEntity<?> mail(@Valid @RequestBody MailDto mailDto) throws MessagingException {
        mailServiceImpl.sendMail(mailDto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
