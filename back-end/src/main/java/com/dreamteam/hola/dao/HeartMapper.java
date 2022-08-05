package com.dreamteam.hola.dao;

import com.dreamteam.hola.dto.BoardDto;
import com.dreamteam.hola.dto.Heart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface HeartMapper {



    Heart findHeart(Heart heart);

    int addHeart(Heart dto);

    void deleteHeart(Long boardId,Long memberId);

    List<BoardDto> heartList(Long memberId);

}
