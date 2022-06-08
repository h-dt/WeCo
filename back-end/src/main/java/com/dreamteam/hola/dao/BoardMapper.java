package com.dreamteam.hola.dao;

import com.dreamteam.hola.domain.Board;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BoardMapper {

    @Results(id="boardResult", value = {
            @Result(property = "viewCnt", column="view_cnt"),
            @Result(property = "regDate", column="reg_date"),
            @Result(property = "modDate", column="mod_date"),
            @Result(property = "recruitStatus", column="recruit_status"),
            @Result(property = "recruitType", column="recruit_type"),
            @Result(property = "recruitCnt", column="recruit_cnt"),
            @Result(property = "startDate", column="start_date")
    })
    @Select("select * from board where id = #{id}")
    Board findById(Long id);
}
