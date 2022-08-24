package com.dreamteam.hola.service;

import com.dreamteam.hola.dao.BoardMapper;
import com.dreamteam.hola.dto.board.BoardReqDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class DummyTest {

    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private BoardServiceImpl boardService;

    @Test
    public void boardDummy(){
        List<String> skillList = new ArrayList<>();
        skillList.add("JPA");
        skillList.add("Vue");
        IntStream.rangeClosed(1,100).forEach(i->{
            BoardReqDto board = BoardReqDto.builder()
                    .title("DummyTitle" + i)
                    .content("DummyContent" + i)
                    .memberId(1L)
                    .recruitType("스터디")
                    .recruitCnt("5명")
                    .progressType("온라인")
                    .duration("5개월")
                    .startDate("2022-10-22")
                    .contactType("카톡")
                    .contact("dummy@Email.com")
                    .skills(skillList)
                    .build();
            boardService.save(1L, board);
        });
        }
    }


