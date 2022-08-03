package com.dreamteam.hola.service;

import com.dreamteam.hola.dao.BoardMapper;
import com.dreamteam.hola.dao.BoardSkillMapper;
import com.dreamteam.hola.dao.CommentMapper;
import com.dreamteam.hola.dao.SkillMapper;
import com.dreamteam.hola.dto.BoardDto;
import com.dreamteam.hola.dto.searchBoardDto;
import com.dreamteam.hola.dto.BoardReqDto;
import com.dreamteam.hola.dto.CommentDto;
import com.dreamteam.hola.dto.RecommendedBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    private final BoardSkillMapper boardSkillMapper;
    private final CommentMapper commentMapper;
    private final SkillMapper skillMapper;

    // Board 1개 가져오기_2022_06_06_by_김우진
    @Override
    public BoardDto getBoard(Long boardId) {
        // memberId 와 boardId로 해당 게시글 정보 조회
        BoardDto findBoard = boardMapper.findById(boardId);
        
        // 조회수 증가
        boardMapper.updateViewCnt(boardId);
        
        // 댓글과 대댓글 조회 및 Dto에 Set
        List<CommentDto> comments = commentMapper.findAllCommentByBoardId(boardId);
        findBoard.setCommentCnt(commentMapper.CountByBoardId(boardId));
        for (CommentDto commentDto : comments) {
            commentDto.setBigCommentCnt(commentMapper.CountBigComments(boardId, commentDto.getCommentId()));
            commentDto.setBigComments(commentMapper.findAllBigCommentByBoardIdAndCGroup(boardId, commentDto.getCommentId()));
        }
        findBoard.setComments(comments);

        // 게시글에 사용된 skill 조회 및 Dto에 set
        List<String> skills = skillMapper.findAllByBoardId(boardId);
        findBoard.setSkills(skills);

        return findBoard;
    }

    // Board 전체 List 가져오기_2022_06_08_by_김우진
    @Override
    public List<BoardDto> getBoards(BoardReqDto boardReqDto) {
        // 모집 타입 + 기술 스택(옵션, 비어있을 수 있음)에 해당되는 게시글 모두 조회
        System.out.println("boardReqDto = " + boardReqDto.toString());
        List<BoardDto> findAll = boardMapper.findAll(boardReqDto.getRecruitType(), boardReqDto.getRecruitStatus(), boardReqDto.getSkills());
        for (BoardDto findBoard : findAll) {
            // 각 게시글에 사용된 기술 스택 set
            List<String> skills = skillMapper.findAllByBoardId(findBoard.getId());
            findBoard.setSkills(skills);
            // 총 댓글 수 Dto에 set
            findBoard.setCommentCnt(commentMapper.CountByBoardId(findBoard.getId()));
        }
        return findAll;
    }

    //Board 게시물 등록하기_2022_06_22_by_정은비
    @Override
    public int register(Long memberId, BoardDto boardDto) {
        boardDto.setMemberId(memberId);
        boardMapper.insertBoard(boardDto);
        Long id = boardDto.getId();

        List<String> skills = boardDto.getSkills();
        Map<String, Long> map = new HashMap<>();
        for (String skillType : skills) {
            map.put("id", id);
            map.put("skill", skillMapper.findBySkillType(skillType));
            boardSkillMapper.insert(map);
        }
        return 1;
    }

    // 모집 마감 토글_2022_06_19_by_김우진
    @Override
    public int updateRecruitStatus(Long memberId, Long id) {
        BoardDto findBoard = boardMapper.findByIdAndMemberId(id, memberId);
        return findBoard.getRecruitStatus().equals("N") ? boardMapper.updateRecruitStatus(id, "Y") : boardMapper.updateRecruitStatus(id, "N");
    }

    @Override
    public int update(Long id, BoardDto boardDto) {
        return boardMapper.update(id, boardDto);
    }


    // 추천게시물 _2022_07_11_by_정은비
    @Override
    public List<RecommendedBoardDto> getRecommendedBoardList() {
        boardMapper.updateCommentCnt();
        boardMapper.calScore();
        List<RecommendedBoardDto> recommendedBoard = boardMapper.selectRecommendedBoard();

        return recommendedBoard;
    }

    @Override
    public List<BoardDto> getMyBoards(Long memberId) {
        return boardMapper.findAllByMemberId(memberId);
    }

    // 게시물 검색 _2022_07_11_by_정은비
    @Override
    public List<BoardDto> searchBoards(searchBoardDto searchBoardDto) {
        List<BoardDto> searchBoards = boardMapper.search(searchBoardDto);
        for (BoardDto searchBoard : searchBoards) {
            // 각 게시글에 사용된 기술 스택 set
            List<String> skills = skillMapper.findAllByBoardId(searchBoard.getId());
            searchBoard.setSkills(skills);
            // 총 댓글 수 Dto에 set
            searchBoard.setCommentCnt(commentMapper.CountByBoardId(searchBoard.getId()));
        }
        return searchBoards;
    }

    //개시물 삭제_2022_07_27_by_정은비
    @Override
    public int delete(Long boardId) {
        boardMapper.deleteBoard(boardId);
        return 1;
    }

}
