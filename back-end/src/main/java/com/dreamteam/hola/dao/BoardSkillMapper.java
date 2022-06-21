package com.dreamteam.hola.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface BoardSkillMapper {
    void insert(Map<String, Long> map);
}
