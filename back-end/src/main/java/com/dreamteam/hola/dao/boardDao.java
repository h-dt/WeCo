package com.dreamteam.hola.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class boardDao {

    @Autowired
    private SqlSession session;
    private static String namespace = "com.dreamteam.hola.dao.BoardMapper.";
}