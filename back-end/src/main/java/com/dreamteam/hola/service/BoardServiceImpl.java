package com.dreamteam.hola.service;

import com.dreamteam.hola.dao.BoardMapper;
import com.dreamteam.hola.domain.Board;
import com.dreamteam.hola.dto.BoardDetailResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;

    // Board 1개 가져오기_2022_06_06_by_김우진
    @Override
    public BoardDetailResDto getBoard(Long id) {
        System.out.println("Service id = " + id);
        Board findBoard = boardMapper.findById(id);
        System.out.println("===================");
        System.out.println("findBoard = " + findBoard.toString());
        BoardDetailResDto boardDetailResDto = BoardDetailResDto.builder()
                .title(findBoard.getTitle())
                .content(findBoard.getContent())
                .contact(findBoard.getContact())
                .recruitType(findBoard.getRecruitType())
                .recruitCnt(findBoard.getRecruitCnt())
                .duration(findBoard.getDuration())
                .regDate(findBoard.getRegDate())
                .startDate(findBoard.getStartDate())
                .build();
        return boardDetailResDto;
    }
}
