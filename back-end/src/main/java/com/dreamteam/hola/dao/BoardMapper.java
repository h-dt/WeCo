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

    int insertBoard(BoardDetailDto dto);

    void insertBoardSkill(Map<String, Integer> map);

//    public List<DTO> 변수명(); - DB데이터를 불러오는 메서드
//    public void 변수명(DTO dto); - DB에 데이터를 저장하는 메소드
}
