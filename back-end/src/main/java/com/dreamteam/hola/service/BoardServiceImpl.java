package com.dreamteam.hola.service;

import com.dreamteam.hola.dao.*;
import com.dreamteam.hola.dto.RecommendedBoardDto;
import com.dreamteam.hola.dto.board.RecommendedBoardDto;
import com.dreamteam.hola.dto.SkillDto;
import com.dreamteam.hola.dto.board.BoardDetailDto;
import com.dreamteam.hola.dto.board.BoardFilterDto;
import com.dreamteam.hola.dto.board.BoardListDto;
import com.dreamteam.hola.dto.board.BoardReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    private final MemberMapper memberMapper;
    private final BoardSkillMapper boardSkillMapper;
    private final CommentMapper commentMapper;
    private final SkillMapper skillMapper;
    private final HeartMapper heartMapper;

    // Board 1개 가져오기_2022_06_06_by_김우진
    @Override
    public BoardDetailDto getBoard(Long boardId) {
        // memberId 와 boardId로 해당 게시글 정보 조회
        BoardDetailDto findBoard = boardMapper.findById(boardId);
        
        // 조회수 증가
        boardMapper.updateViewCnt(boardId);

        findBoard.setCommentCnt(commentMapper.CountByBoardId(boardId));

        // 게시글에 사용된 skill 조회 및 Dto에 set
        List<String> skills = skillMapper.findAllByBoardId(boardId);
        findBoard.setSkills(skills);

        return findBoard;
    }

    // Board 전체 List 가져오기_2022_06_08_by_김우진
    @Override
    public List<BoardListDto> getBoards(BoardFilterDto boardReqDto) {
        // 모집 타입 + 기술 스택(옵션, 비어있을 수 있음)에 해당되는 게시글 모두 조회
        System.out.println("boardReqDto = " + boardReqDto.toString());
        List<BoardListDto> findAll = boardMapper.findAll(boardReqDto.getRecruitType(), boardReqDto.getRecruitStatus(), boardReqDto.getSkills());
        for (BoardListDto findBoard : findAll) {
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
    @Transactional
    public int save(Long memberId, BoardReqDto boardDto) {
            boardDto.setMemberId(memberId);
            boardMapper.save(boardDto);

            Long id = boardDto.getId();
            log.info(boardDto.getSkills().toString());

            List<String> skillList = boardDto.getSkills();//[]
            log.info(skillList.toString());

            skillList.forEach(skills->{
                SkillDto skillDto = skillMapper.findBySkillType(skills);
                boardSkillMapper.save(id,skillDto.getSkillId());
            });

            return 1;
    }

    // 모집 마감 토글_2022_06_19_by_김우진
    @Override
    public int updateRecruitStatus(Long memberId, Long id) {
//        try{
            BoardDetailDto findBoard = boardMapper.findByIdAndMemberId(id, memberId);
            return findBoard.getRecruitStatus().equals("N") ? boardMapper.updateRecruitStatus(id, "Y") : boardMapper.updateRecruitStatus(id, "N");
//        } catch()
    }

    @Override
    @Transactional
    public int update(Long memberId, BoardReqDto boardDto) {

        if(boardMapper.findById(boardDto.getId()) == null){
            throw new NullPointerException();
        }

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String updateDate = dateFormat.format(date);

        boardDto.setModDate(updateDate);
        boardMapper.update(memberId, boardDto);
        boardSkillMapper.deleteAllByBoardId(boardDto.getId());
        boardDto.getSkills().forEach(skills -> {
            SkillDto skillDto = skillMapper.findBySkillType(skills);
            boardSkillMapper.save(boardDto.getId(),skillDto.getSkillId());
        });
        return 1;
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
    public List<BoardListDto> getMyBoards(Long memberId) {
        return boardMapper.findAllByMemberId(memberId);
    }




}
