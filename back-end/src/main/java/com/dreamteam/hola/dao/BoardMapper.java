package com.dreamteam.hola.dao;

import com.dreamteam.hola.dto.BoardDto;
import com.dreamteam.hola.dto.BoardDetailDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    BoardDetailDto findById(Long id);

    List<BoardDto> findAllByRecruitType(String recruitType);

    void updateBoardViewCnt(Long id);
}
