package com.dreamteam.hola.service;

import com.dreamteam.hola.domain.Board;
import com.dreamteam.hola.dto.BoardDto;

import java.util.List;

public interface BoardService {

    BoardDto getBoard(Long id);

    List<Board> getBoardList();

    int register(BoardDetailResDto boardDetailResDto) throws Exception;
}
