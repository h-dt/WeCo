package com.dreamteam.hola.dao;

import com.dreamteam.hola.dto.Heart;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface HeartMapper {



    void addHeart(Heart dto);

    void deleteHeart(Heart dto);

    Heart findByBidMid(Long boardId,Long memberId);
}
