package com.dreamteam.hola.service;

import com.dreamteam.hola.dto.BoardDto;
import com.dreamteam.hola.dto.BoardDetailDto;

import java.util.List;

public interface BoardService {

    BoardDetailDto getBoard(Long id);

    List<BoardDto> getBoardListByRecruitType(String recruitType);

    List<BoardDto> getBoardListBySkillType(List<String> skills);

    int updateRecruitStatus(Long id);

    int update(Long id, BoardDetailDto boardDetailDto);
}
