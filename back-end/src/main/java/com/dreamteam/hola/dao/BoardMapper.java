package com.dreamteam.hola.dao;

import com.dreamteam.hola.dto.BoardDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    BoardDto findById(Long memberId, Long id);

    List<BoardDto> findAllByRecruitType(String recruitType);

    void updateBoardViewCnt(Long id);

    void insertBoard(BoardDto boardDto);

    List<BoardDto> findAllBySkillTypes(List<String> skillTypes);

    int updateRecruitStatus(Long id, String status);

    int update(Long id, BoardDto board);

    List<BoardDto> findAllByMemberId(Long memberId);
}
