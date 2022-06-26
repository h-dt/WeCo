package com.dreamteam.hola.dao;

import com.dreamteam.hola.dto.BoardDetailDto;
import com.dreamteam.hola.dto.BoardDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    BoardDetailDto findById(Long id);

    List<BoardDto> findAllByRecruitType(String recruitType);

    void updateBoardViewCnt(Long id);

    void insertBoard(BoardDto boardDto);

    List<BoardDto> findAllBySkillTypes(List<String> skillTypes);

    int updateRecruitStatus(Long id, String status);

    int update(Long id, BoardDetailDto board);
}
