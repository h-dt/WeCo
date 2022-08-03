package com.dreamteam.hola.service;

import com.dreamteam.hola.dto.BoardDto;
import com.dreamteam.hola.dto.Heart;
import com.dreamteam.hola.dto.RecommendedBoardDto;
import com.dreamteam.hola.dto.BoardReqDto;

import java.util.List;

public interface BoardService {

    BoardDto getBoard(Long boardId);

    int register(Long memberId, BoardDto dto);
  
    List<BoardDto> getBoards(BoardReqDto boardReqDto);

//    List<BoardDto> getBoardListBySkillType(List<String> skills);

    int updateRecruitStatus(Long memberId, Long id);

    int update(Long id, BoardDto BoardDto);
    
    List<RecommendedBoardDto> getRecommendedBoardList();

    List<BoardDto> getMyBoards(Long memberId);





}
