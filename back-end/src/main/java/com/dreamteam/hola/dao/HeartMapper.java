package com.dreamteam.hola.dao;

import com.dreamteam.hola.dto.Heart;
import com.dreamteam.hola.dto.board.BoardListDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HeartMapper {



    Heart findHeart(Heart heart);

    int addHeart(Heart dto);

    void deleteHeart(Long boardId,Long memberId);

    List<BoardListDto> heartList(Long memberId);

}
