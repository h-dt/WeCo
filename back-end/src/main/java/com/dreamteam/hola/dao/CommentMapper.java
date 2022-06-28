package com.dreamteam.hola.dao;

import com.dreamteam.hola.domain.BigComment;
import com.dreamteam.hola.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentDto> findAllCommentByBoardId(Long id);

    int CountByBoardId(Long id);

    int save(CommentDto commentDto);

    int update(CommentDto comment);

    List<BigComment> findAllBigCommentByBoardIdAndCGroup(Long boardId, Long cGroup);

    int CountBigComments(Long id, Long cGroup);
}
