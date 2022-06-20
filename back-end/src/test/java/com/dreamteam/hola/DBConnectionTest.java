//package com.dreamteam.hola;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.sql.Connection;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
//public class ConnectionTests {
//
//    @Autowired
//    private SqlSessionFactory sqlSessionFactory;
//
//    @Test
//    public void connection_test(){
//        try(Connection con = sqlSessionFactory.openSession().getConnection()){
//            System.out.println("커넥션 성공");
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//}