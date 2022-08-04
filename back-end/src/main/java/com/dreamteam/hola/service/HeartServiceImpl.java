package com.dreamteam.hola.service;

import com.dreamteam.hola.dao.HeartMapper;
import com.dreamteam.hola.dao.SkillMapper;
import com.dreamteam.hola.dto.BoardDto;
import com.dreamteam.hola.dto.Heart;
import com.dreamteam.hola.util.DeduplicationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeartServiceImpl {

    private final HeartMapper heartMapper;
    private final SkillMapper skillMapper;

    public int save(Long boardId,Long memberId){
        Heart heart = Heart.builder()
                .boardId(boardId)
                .memberId(memberId)
                .build();

         return heartMapper.addHeart(heart);

    }

    public void delete(Long boardId,Long memberId){
        heartMapper.deleteHeart(boardId,memberId);
    }

    public List<BoardDto> HeartList(Long memberId){

        List<BoardDto> heartList = heartMapper.heartList(memberId);

        List<BoardDto> distinctedList = DeduplicationUtils.deduplication(heartList, BoardDto::getId);
        for (BoardDto boardDto : distinctedList) {
            List<String> skills = skillMapper.findAllByBoardId(boardDto.getId());
            boardDto.setSkills(skills);
        }

        return  distinctedList;
    }
}
