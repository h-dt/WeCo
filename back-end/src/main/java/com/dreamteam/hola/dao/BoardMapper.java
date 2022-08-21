package com.dreamteam.hola.dao;

import com.dreamteam.hola.dto.board.BoardDetailDto;
import com.dreamteam.hola.dto.board.BoardListDto;
import com.dreamteam.hola.dto.board.BoardReqDto;
import com.dreamteam.hola.dto.board.RecommendedBoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    BoardDetailDto findById(Long id);

    BoardDetailDto findByIdAndMemberId(Long id, Long memberId);


    List<BoardListDto> findAll(String recruitType, String recruitStatus, List<String> skillTypes,int startRowNum,int rowCount);

    void updateViewCnt(Long id);

    int save(BoardReqDto boardDto);

    int updateRecruitStatus(Long id, String status);

    int update(Long memberId, BoardReqDto boardDto);


    void calScore();

    List<RecommendedBoardDto> selectRecommendedBoard();

    void updateCommentCnt();

    List<BoardListDto> findAllByMemberId(Long memberId);

    int delete(BoardReqDto boardDto);

}
