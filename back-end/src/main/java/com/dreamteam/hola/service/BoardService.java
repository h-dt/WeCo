package com.dreamteam.hola.service;

import com.dreamteam.hola.dto.BoardDto;
import com.dreamteam.hola.dto.BoardDetailDto;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BoardService {

    BoardDetailDto getBoard(Long id);


    int register(BoardDto dto);
  
    List<BoardDto> getBoardListByRecruitType(String recruitType);

    List<BoardDto> getBoardListBySKillType(List<String> skills);
}
