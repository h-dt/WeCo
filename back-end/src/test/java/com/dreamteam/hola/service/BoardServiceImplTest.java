//package com.dreamteam.hola.service;
//
//import com.dreamteam.hola.dao.BoardSkillMapper;
//import com.dreamteam.hola.dao.SkillMapper;
////import com.dreamteam.hola.dto.BoardDto;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.text.DateFormat;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@RunWith(SpringRunner.class)
//@Transactional
//class BoardServiceImplTest {
//
//    @Autowired
//    private BoardServiceImpl boardService;
//
//    @Autowired
//    private BoardSkillMapper boardSkillMapper;
//
//    @Autowired
//    private SkillMapper skillMapper;
//    @Test
//    void register() {
//
//        //given
//        List<String> skillList = new ArrayList<>();
//        skillList.add("JPA");
//        skillList.add("Vue");
//        Date date = new Date(System.currentTimeMillis());
//        BoardDto board = BoardDto.builder()
//                .memberId(1L)
//                .title("testTitle")
//                .content("testContent")
//                .recruitType("프로젝트")
//                .recruitCnt("7명")
//                .progressType("온라인")
//                .duration("2개월")
//                .contactType("email")
//                .contact("010-1234-1234")
//                .skills(skillList)
//                .startDate(date)
//                .build();
//        //when
//        boardService.register(1L,board);
//
//        //then
//        Assertions.assertThat("testTitle").isEqualTo(board.getTitle());
//    }
//
//    @Test
//    @DisplayName("board_skill 에 skill 넣기")
//    void insertSkills(){
//        boardSkillMapper.save(1L,1L);
//    }
//
//    @Test
//    @DisplayName("bo")
//    void register1(){
//
//    }
//}