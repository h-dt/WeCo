package com.dreamteam.hola.service;

import com.dreamteam.hola.dto.BoardDto;
import com.dreamteam.hola.dto.RecommendedBoardDto;

import java.util.List;

public interface BoardService {

    BoardDto getBoard(Long memberId, Long boardId);

    int register(Long memberId, BoardDto dto);
  
    List<BoardDto> getBoardListByRecruitType(String recruitType);

    List<BoardDto> getBoardListBySkillType(List<String> skills);

    int updateRecruitStatus(Long id);

    int update(Long id, BoardDto BoardDto);

    List<RecommendedBoardDto> getRecommendedBoardList();
}
