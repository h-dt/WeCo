package com.dreamteam.hola.dao;

import com.dreamteam.hola.domain.Board;
import com.dreamteam.hola.dto.BoardDetailResDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> findAll();

    BoardDetailResDto findById(Long id);

    int insert(BoardDetailResDto boardDetailResDto);

//    public List<DTO> 변수명(); - DB데이터를 불러오는 메서드
//    public void 변수명(DTO dto); - DB에 데이터를 저장하는 메소드
}
