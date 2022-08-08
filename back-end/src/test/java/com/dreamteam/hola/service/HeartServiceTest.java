package com.dreamteam.hola.service;

import com.dreamteam.hola.dao.BoardMapper;
import com.dreamteam.hola.dao.HeartMapper;
import com.dreamteam.hola.dao.MemberMapper;
import com.dreamteam.hola.dto.BoardDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class HeartServiceTest {

    @Autowired
    private HeartServiceImpl heartService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private HeartMapper heartMapper;
    @Autowired
    private BoardMapper boardMapper;



    @Test
    @DisplayName("좋아요 테스트")
    @WithMockUser(roles = "USER")
    public void saveHeart() throws Exception {
        //given
        BoardDto boardDto = addBoard();
        //when
//        mockMvc.perform(post("/heart/"+boardDto.getId()))
//                .andExpect(status().isOk());

//        mockMvc.perform(post("/member/" +boardDto.getId()))
//                .andExpect(status().isBadRequest());
        List<BoardDto> heartBoard = heartMapper.heartList(1L);




        //then
        assertThat(boardDto.getTitle()).isEqualTo( heartBoard.get(1).getTitle());
    }


    private BoardDto addBoard(){

        List<String> skillList = new ArrayList<>();
        skillList.add("Vue");
        skillList.add("JPA");

        BoardDto boardDto = BoardDto.builder()
                .id(4L)
                .memberId(1L)
                .writer("codeJ")
                .writerProfile("default Image")
                .title("Heart Title")
                .content("Heart Content")
                .recruitCnt("3명")
                .recruitType("스터디")
                .progressType("오프라인")
                .duration("6개월")
                .contactType("카카오톡")
                .contact("kakao.com")
                .skills(skillList)
                .startDate(new Date(System.currentTimeMillis()))
                .build();

        boardMapper.insertBoard(boardDto);

        BoardDto result = boardMapper.findById(4L);
        return result;
    }
}
