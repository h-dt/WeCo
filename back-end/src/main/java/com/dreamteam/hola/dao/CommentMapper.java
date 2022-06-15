package com.dreamteam.hola.dao;

import com.dreamteam.hola.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<Comment> findAllByBoardId(Long id);

    int CountByBoardId(Long id);
}
