package com.dreamteam.hola.dao;

import com.dreamteam.hola.dto.comment.CommentDto;
import com.dreamteam.hola.dto.comment.CommentReqDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentDto> findAllCommentByBoardId(Long id);

    Long CountByBoardId(Long id);

    int save(Long memberId, CommentReqDto commentDto);

    int update(Long memberId, CommentReqDto commentDto);

    CommentDto findById(Long id);
}
