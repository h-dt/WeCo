package com.dreamteam.hola.service;

import com.dreamteam.hola.domain.Board;
import com.dreamteam.hola.dto.BoardDetailResDto;

import java.util.List;

public interface BoardService {

    BoardDetailResDto getBoard(Long id);

    List<Board> getBoardList();

    int write(BoardDetailResDto boardDetailResDto);
}
