package com.dreamteam.hola.service;

import com.dreamteam.hola.dao.BoardMapper;
import com.dreamteam.hola.domain.Board;
import com.dreamteam.hola.dto.BoardDetailResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;

    // Board 1개 가져오기_2022_06_06_by_김우진
    @Override
    public BoardDetailResDto getBoard(Long id) {
        return boardMapper.findById(id);
    }

    // Board 전체 List 가져오기_2022_06_08_by_김우진
    public List<Board> getBoardList() {
        return boardMapper.findAll();
    }
}
