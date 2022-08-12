package com.dreamteam.hola.service;

import com.dreamteam.hola.dao.CommentMapper;
import com.dreamteam.hola.dao.MemberMapper;
import com.dreamteam.hola.dto.CommentDto;
import com.dreamteam.hola.dto.member.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl {

    private final CommentMapper commentMapper;
    private final MemberMapper memberMapper;

    // Comment 생성하기_2022_06_17_by_김우진
    @Transactional
    public int save(Long memberId, CommentDto commentDto) {
        return commentMapper.save(memberId, commentDto);
    }
    
    // Comment 수정하기_2022_06_19_by_김우진
    @Transactional
    public int update(Long memberId, Long id, CommentDto commentDto) {
        try{
            MemberDto findMember = memberMapper.findById(memberId);
            CommentDto findComment = commentMapper.findById(id);
            if(findMember.getNickname().equals(findComment.getWriter())){
                return commentMapper.update(commentDto);
            } else {
                return 0;
            }
        } catch(NoSuchElementException e){
            log.error("찾아 오는 중 오류 발생 !! : " + Arrays.toString(e.getStackTrace()));
            throw new NoSuchElementException("해당되는 데이터가 존재하지 않습니다.");
        } catch(RuntimeException e){
            log.error("댓글 수정 중 오류 발생 !! : " + Arrays.toString(e.getStackTrace()));
            throw new RuntimeException("댓글 수정 중 오류 발생", e.getCause());
        }
    }
}