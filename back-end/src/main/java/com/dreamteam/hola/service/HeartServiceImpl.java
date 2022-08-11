package com.dreamteam.hola.service;

import com.dreamteam.hola.dao.HeartMapper;
import com.dreamteam.hola.dao.SkillMapper;
import com.dreamteam.hola.dto.Heart;
import com.dreamteam.hola.dto.board.BoardListDto;
import com.dreamteam.hola.util.DeduplicationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HeartServiceImpl {

    private final HeartMapper heartMapper;
    private final SkillMapper skillMapper;

    public boolean save(Long boardId,Long memberId){
        Heart heart = Heart.builder()
                .boardId(boardId)
                .memberId(memberId)
                .build();


        if(isNotAlreadyList(heart) != null){
            heartMapper.addHeart(heart);
            return true;
        }
         return false;

    }

    public void delete(Long boardId,Long memberId){
        heartMapper.deleteHeart(boardId,memberId);
    }

    public List<BoardListDto> HeartList(Long memberId){

        List<BoardListDto> heartList = heartMapper.heartList(memberId);

        List<BoardListDto> distinctedList = DeduplicationUtils.deduplication(heartList, BoardListDto::getId);
        for (BoardListDto boardDto : distinctedList) {
            List<String> skills = skillMapper.findAllByBoardId(boardDto.getId());
            boardDto.setSkills(skills);
        }

        return  distinctedList;
    }

    private Heart isNotAlreadyList(Heart heart){
        return heartMapper.findHeart(heart);

    }
}
