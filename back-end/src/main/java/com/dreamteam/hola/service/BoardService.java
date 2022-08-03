package com.dreamteam.hola.service;

import com.dreamteam.hola.dto.BoardDto;
import com.dreamteam.hola.dto.RecommendedBoardDto;
import com.dreamteam.hola.dto.BoardReqDto;
import com.dreamteam.hola.dto.searchBoardDto;

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

    // 게시물 검색 _2022_07_11_by_정은비
    List<BoardDto> searchBoards(searchBoardDto searchBoardDto);

    //개시물 삭제_2022_07_27_by_정은비
    int delete(Long boardId);
}
