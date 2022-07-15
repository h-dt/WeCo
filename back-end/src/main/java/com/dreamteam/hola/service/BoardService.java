package com.dreamteam.hola.service;

import com.dreamteam.hola.dto.BoardDto;
import com.dreamteam.hola.dto.BoardReqDto;

import java.util.List;

public interface BoardService {

    BoardDto getBoard(Long boardId);

    int register(BoardDto dto);
  
    List<BoardDto> getBoards(BoardReqDto boardReqDto);

//    List<BoardDto> getBoardListBySkillType(List<String> skills);

    int updateRecruitStatus(Long memberId, Long id);

    int update(Long id, BoardDto BoardDto);

    List<BoardDto> getMyBoards(Long memberId);
}
