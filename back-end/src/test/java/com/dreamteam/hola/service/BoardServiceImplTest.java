package com.dreamteam.hola.service;

import com.dreamteam.hola.dto.BoardDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@SpringBootTest
@Transactional
class BoardServiceImplTest {

    @Autowired
    private BoardServiceImpl boardService;

    @Test
    void register() {

        Date date = new Date(System.currentTimeMillis());
        BoardDto board = BoardDto.builder()
                .memberId(1L)
                .title("testTitle")
                .content("testContent")
                .recruitType("프로젝트")
                .recruitCnt("7명")
                .progressType("온라인")
                .duration("2개월")
                .contactType("email")
                .contact("010-1234-1234")
                .startDate(date)
                .build();

        boardService.register(1L,board);



    }
}