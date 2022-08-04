package com.dreamteam.hola.dao;

import com.dreamteam.hola.dto.BoardDto;
import com.dreamteam.hola.dto.RecommendedBoardDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    BoardDto findById(Long id);

    BoardDto findByIdAndMemberId(Long id, Long memberId);


    List<BoardDto> findAll(String recruitType, String recruitStatus, List<String> skillTypes);

    void updateViewCnt(Long id);

    void insertBoard(BoardDto boardDto);

    int updateRecruitStatus(Long id, String status);

    int update(Long id, BoardDto board);


    void calScore();

    List<RecommendedBoardDto> selectRecommendedBoard();

    void updateCommentCnt();

    List<BoardDto> findAllByMemberId(Long memberId);

}
