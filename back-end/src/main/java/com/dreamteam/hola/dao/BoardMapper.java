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
}
