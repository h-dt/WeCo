package com.dreamteam.hola.service;

import com.dreamteam.hola.dao.BoardMapper;
import com.dreamteam.hola.dao.CommentMapper;
import com.dreamteam.hola.dao.SkillMapper;
import com.dreamteam.hola.dto.BoardDto;
import com.dreamteam.hola.domain.Comment;
import com.dreamteam.hola.domain.Skill;
import com.dreamteam.hola.dto.BoardDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;
    private final CommentMapper commentMapper;
    private final SkillMapper skillMapper;

    // Board 1개 가져오기_2022_06_06_by_김우진
    @Override
    public BoardDetailDto getBoard(Long id) {
        BoardDetailDto findBoard = boardMapper.findById(id);
        boardMapper.updateBoardViewCnt(id);
        List<Comment> comments = commentMapper.findAllByBoardId(id);
        List<Skill> skills = skillMapper.findAllByBoardId(id);
        List<String> transSkills = new ArrayList<>();
        findBoard.setComments(comments);
        for (Skill skill : skills) {
            transSkills.add(skill.getSkillType());
        }
        findBoard.setSkills(transSkills);
        return findBoard;
    }

    @Override
    // Board 전체 List 가져오기_2022_06_08_by_김우진
    public List<BoardDto> getBoardListByRecruitType(String recruitType) {
        List<BoardDto> allByRecruitType = boardMapper.findAllByRecruitType(recruitType);
        for (BoardDto boardDto : allByRecruitType) {
            List<Skill> skills = skillMapper.findAllByBoardId(boardDto.getId());
            List<String> transSkills = new ArrayList<>();
            for (Skill skill : skills) {
                transSkills.add(skill.getSkillType());
            }
            boardDto.setCommentCnt(commentMapper.CountByBoardId(boardDto.getId()));
            boardDto.setSkills(transSkills);
        }
        return allByRecruitType;
    }

    @Override
    public List<BoardDto> getBoardListBySKillType(List<String> skills) {
        List<String> skillTypes = new ArrayList<>(skills);
        return boardMapper.findAllBySkillTypes(skillTypes);
    }
}
