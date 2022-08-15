package com.dreamteam.hola.mappers;

import com.dreamteam.hola.dao.BoardMapper;
import com.dreamteam.hola.dto.board.BoardDetailDto;
import com.dreamteam.hola.dto.board.BoardReqDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class BoardMapperTest {

    @Autowired
    BoardMapper boardMapper;

    @BeforeEach
    public void setup() {
    }

    @Test
    @DisplayName("게시글 등록")
    @Transactional
    @Rollback(value = false)
    void save() {
        // given
        BoardReqDto requestBoard = BoardReqDto.builder()
                .memberId(1L)
                .title("999번 게시글 title")
                .content("999번 게시글 content")
                .contactType("이메일")
                .contact("ssldfkjl@naver.com")
                .recruitType("프로젝트")
                .recruitCnt("10명")
                .progressType("온라인")
                .duration("10개월")
//                .startDate(new Date())
                .build();

        // when

        boardMapper.save(requestBoard);
        BoardDetailDto findDto = boardMapper.findById(4L);

        // then
        Assertions.assertThat(findDto.getId()).isEqualTo(4L);

    }

    @Test
    @DisplayName("게시글 모집 마감이 Y에서 N으로 바뀌는 지")
    @Transactional
    @Rollback(value = false)
    void updateRecruitStatusToN() {

        //given
        BoardReqDto requestBoard = BoardReqDto.builder()
                .memberId(1L)
                .title("999번 게시글 title")
                .content("999번 게시글 content")
                .contactType("이메일")
                .contact("ssldfkjl@naver.com")
                .recruitType("프로젝트")
                .recruitCnt("10명")
                .progressType("온라인")
                .duration("10개월")
//                .startDate(new Date())
                .build();

        boardMapper.save(requestBoard);
        BoardDetailDto findDto = boardMapper.findById(requestBoard.getId());

        //when
        boardMapper.updateRecruitStatus(findDto.getId(), "N");
        findDto = boardMapper.findById(requestBoard.getId());

        //then
        Assertions.assertThat(findDto.getRecruitStatus()).isEqualTo("N");
    }
}
