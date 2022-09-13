package com.dreamteam.hola.service;

import com.dreamteam.hola.dao.BoardMapper;
import com.dreamteam.hola.dao.CommentMapper;
import com.dreamteam.hola.dto.board.BoardDetailDto;
import com.dreamteam.hola.dto.comment.CommentDto;
import com.dreamteam.hola.dto.comment.CommentReqDto;
import com.dreamteam.hola.dto.comment.CommentResDto;
import com.dreamteam.hola.dto.comment.CommentUpdateDto;
import com.dreamteam.hola.exception.comment.BoardNotFoundException;
import com.dreamteam.hola.exception.comment.CommentNotMatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl {

    private final CommentMapper commentMapper;
    private final BoardMapper boardMapper;

    // Comment 생성하기_2022_06_17_by_김우진
    @Transactional
    public int save(Long memberId, CommentReqDto commentDto) {
        BoardDetailDto findBoard = boardMapper.findById(commentDto.getBoardId());
        if(findBoard == null){
            throw new BoardNotFoundException("게시글이 존재하지 않습니다.");
        }
        return commentMapper.save(memberId, commentDto);
    }

    // Comment 수정하기_2022_06_19_by_김우진
    @Transactional
    public int update(Long memberId, CommentUpdateDto commentDto) {
        CommentDto findComment = commentMapper.findById(commentDto.getCommentId());
        if (boardMapper.findById(commentDto.getBoardId()) == null) {
            throw new BoardNotFoundException("게시글이 존재하지 않습니다.");
        } else if (findComment == null) {
            throw new NullPointerException();
        } else if (!Objects.equals(findComment.getBoardId(), commentDto.getBoardId())) {
            throw new CommentNotMatchException("댓글에 해당되는 게시글 번호가 아닙니다.");
        }

//        Date date = Calendar.getInstance().getTime();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String updateDate = dateFormat.format(date);

//        commentDto.setModDate(updateDate);
        return commentMapper.update(memberId, commentDto);
    }

    public List<CommentResDto> getComments(Long boardId) {
        return commentMapper.findAllCommentByBoardId(boardId);
    }

    @Transactional
    public int delete(Long memberId, CommentUpdateDto commentDto) {
        log.info("댓글 삭제 = ");
        CommentDto findComment = commentMapper.findById(commentDto.getCommentId());

        if (boardMapper.findById(commentDto.getBoardId()) == null) {
            throw new BoardNotFoundException("게시글이 존재하지 않습니다.");
        } else if (findComment == null) {
            throw new NullPointerException();
        } else if (!Objects.equals(findComment.getBoardId(), commentDto.getBoardId())) {
            throw new CommentNotMatchException("댓글에 해당되는 게시글 번호가 아닙니다.");
        }
        return commentMapper.delete(memberId, commentDto);
    }
}