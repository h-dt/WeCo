package com.dreamteam.hola.service;

import com.dreamteam.hola.dto.board.BoardFilterDto;
import com.dreamteam.hola.dto.RecommendedBoardDto;
import com.dreamteam.hola.dto.board.RecommendedBoardDto;
import com.dreamteam.hola.dto.board.*;

import java.util.List;

public interface BoardService {

    BoardDetailDto getBoard(Long boardId);

    int save(Long memberId, BoardReqDto boardReqDto);
  
    List<BoardListDto> getBoards(BoardFilterDto boardReqDto);

//    List<BoardDto> getBoardListBySkillType(List<String> skills);

    int updateRecruitStatus(Long memberId, Long id);

    int update(Long memberId, BoardReqDto BoardReqDto);
    
    List<RecommendedBoardDto> getRecommendedBoardList();

    List<BoardListDto> getMyBoards(Long memberId);





}
