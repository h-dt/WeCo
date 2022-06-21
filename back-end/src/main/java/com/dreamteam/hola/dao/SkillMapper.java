package com.dreamteam.hola.dao;

import com.dreamteam.hola.domain.Skill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SkillMapper {
    List<Skill> findAllByBoardId(Long id);

    long selectId(String skill);
}
