package com.dreamteam.hola.dao;

import com.dreamteam.hola.dto.comment.CommentDto;
import com.dreamteam.hola.dto.comment.CommentReqDto;
import com.dreamteam.hola.dto.comment.CommentResDto;
import com.dreamteam.hola.dto.comment.CommentUpdateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentResDto> findAllCommentByBoardId(Long id);

    Long CountByBoardId(Long id);

    int save(Long memberId, CommentReqDto commentDto);

    int update(Long memberId, CommentUpdateDto commentDto);

    CommentDto findById(Long commentId);

    int delete(Long memberId, Long commentId);
}
