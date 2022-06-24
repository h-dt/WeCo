package com.dreamteam.hola.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SkillMapper {
    List<String> findAllByBoardId(Long id);

    long skillTypeToId(String skill);
}
