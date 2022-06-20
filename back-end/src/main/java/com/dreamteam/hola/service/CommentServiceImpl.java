package com.dreamteam.hola.service;

import com.dreamteam.hola.dao.CommentMapper;
import com.dreamteam.hola.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl {

    private final CommentMapper commentMapper;

    // Comment 생성하기_2022_06_17_by_김우진
    public int save(CommentDto commentDto) {
        return commentMapper.save(commentDto);
    }
    
    // Comment 수정하기_2022_06_19_by_김우진
    public int update(CommentDto commentDto) {
        return commentMapper.update(commentDto);
    }
}