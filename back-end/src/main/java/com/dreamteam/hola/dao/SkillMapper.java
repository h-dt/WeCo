package com.dreamteam.hola.dao;

import com.dreamteam.hola.dto.SkillDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SkillMapper {
    List<String> findAllByBoardId(Long id);

    SkillDto findBySkillType(String skillType);
}
