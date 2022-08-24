package com.dreamteam.hola.service;

import com.dreamteam.hola.dao.BoardMapper;
import com.dreamteam.hola.dao.HeartMapper;
import com.dreamteam.hola.dao.SkillMapper;
import com.dreamteam.hola.dto.Heart;
import com.dreamteam.hola.dto.board.BoardHeartDto;
import com.dreamteam.hola.util.DeduplicationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class HeartServiceImpl {

    private final HeartMapper heartMapper;
    private final SkillMapper skillMapper;
    private final BoardMapper boardMapper;

    public boolean save(Long boardId,Long memberId){

        if(boardMapper.findById(boardId) == null){
            throw new NullPointerException();
        }

        Heart heart = Heart.builder()
                .boardId(boardId)
                .memberId(memberId)
                .build();


        log.info("좋아요 존재해? ={}",isNotAlreadyList(heart));
        if(isNotAlreadyList(heart) == null){
            heartMapper.addHeart(heart);
            return true;
        }
         return false;

    }

    public boolean delete(Long boardId,Long memberId){
        Heart heart = Heart.builder()
                .boardId(boardId)
                .memberId(memberId)
                .build();

        if(isNotAlreadyList(heart) != null){
            heartMapper.deleteHeart(heart);
            return true;
        }
        return false;
    }

    public List<BoardHeartDto> HeartList(Long memberId){

        List<BoardHeartDto> heartList = heartMapper.heartList(memberId);

        List<BoardHeartDto> distinctedList = DeduplicationUtils.deduplication(heartList, BoardHeartDto::getId);
        for (BoardHeartDto boardDto : distinctedList) {
            List<String> skills = skillMapper.findAllByBoardId(boardDto.getId());
            boardDto.setSkills(skills);
        }

        return  distinctedList;
    }

    private Heart isNotAlreadyList(Heart heart){
        return heartMapper.findHeart(heart);

    }
}
