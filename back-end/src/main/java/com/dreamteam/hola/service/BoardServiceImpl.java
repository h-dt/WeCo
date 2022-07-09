package com.dreamteam.hola.service;

import com.dreamteam.hola.dao.BoardMapper;
import com.dreamteam.hola.dao.BoardSkillMapper;
import com.dreamteam.hola.dao.CommentMapper;
import com.dreamteam.hola.dao.SkillMapper;
import com.dreamteam.hola.dto.BoardDto;
import com.dreamteam.hola.dto.CommentDto;
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
    public BoardDto getBoard(Long memberId, Long boardId) {
        BoardDto findBoard = boardMapper.findById(boardId, memberId);
        boardMapper.updateBoardViewCnt(boardId);
        List<CommentDto> comments = commentMapper.findAllCommentByBoardId(boardId);
        findBoard.setCommentCnt(commentMapper.CountByBoardId(boardId));
        for (CommentDto commentDto : comments) {
            commentDto.setBigCommentCnt(commentMapper.CountBigComments(boardId, commentDto.getCommentId()));
//            commentDto.setBigComments(commentMapper.findAllBigCommentByBoardIdAndCGroup(boardId, commentDto.getCommentId()));
        }
        findBoard.setComments(comments);
        List<String> skills = skillMapper.findAllByBoardId(boardId);
        findBoard.setSkills(skills);

        return findBoard;
    }

    // Board 전체 List 가져오기_2022_06_08_by_김우진
    @Override
    public List<BoardDto> getBoardListByRecruitType(String recruitType) {
        List<BoardDto> allByRecruitType = boardMapper.findAllByRecruitType(recruitType);
        for (BoardDto boardDto : allByRecruitType) {
            List<String> skills = skillMapper.findAllByBoardId(boardDto.getId());
            boardDto.setCommentCnt(commentMapper.CountByBoardId(boardDto.getId()));
            boardDto.setSkills(skills);
        }
        return allByRecruitType;
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

    // SkillType으로 Board 조회_2022_06_17_by_김우진
    @Override
    public List<BoardDto> getBoardListBySkillType(List<String> skills) {
        List<BoardDto> allBySkillTypes = boardMapper.findAllBySkillTypes(skills);
        for (BoardDto boardDto : allBySkillTypes) {
            List<String> skillTypes = skillMapper.findAllByBoardId(boardDto.getId());
            boardDto.setCommentCnt(commentMapper.CountByBoardId(boardDto.getId()));
            boardDto.setSkills(skillTypes);
        }
        return allBySkillTypes;
    }

    // 모집 마감 토글_2022_06_19_by_김우진
    @Override
    public int updateRecruitStatus(Long id) {
//        BoardDto findBoard = boardMapper.findById(id);
//        return findBoard.getRecruitStatus().equals("N") ? boardMapper.updateRecruitStatus(id, "Y") : boardMapper.updateRecruitStatus(id, "N");
        return 1;
    }

    @Override
    public int update(Long id, BoardDto boardDto) {
        return boardMapper.update(id, boardDto);
    }
}
