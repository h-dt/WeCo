package com.dreamteam.hola.dao;

import com.dreamteam.hola.dto.Heart;
import com.dreamteam.hola.dto.board.BoardHeartDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HeartMapper {



    Heart findHeart(Heart heart);

    int addHeart(Heart dto);

    int deleteHeart(Heart dto);

    List<BoardHeartDto> heartList(Long memberId);

}
