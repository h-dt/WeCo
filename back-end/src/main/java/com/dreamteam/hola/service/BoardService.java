package com.dreamteam.hola.service;

import com.dreamteam.hola.dto.BoardDto;
import com.dreamteam.hola.dto.BoardDetailDto;

import java.util.List;

public interface BoardService {

    BoardDetailDto getBoard(Long id);

    List<BoardDto> getBoardList(String recruitType);

    int register(BoardDto dto);

}
