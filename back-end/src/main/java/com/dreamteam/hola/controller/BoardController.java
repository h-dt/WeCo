package com.dreamteam.hola.controller;

import com.dreamteam.hola.dto.BoardDto;
import com.dreamteam.hola.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardController {

//    private SqlSession session;
//    private static String namespace = "com.fastcampus.ch4.dao.BoardMapper.";

    private final BoardServiceImpl boardServiceimpl;

    @GetMapping("/board/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {
        BoardDto findBoard = boardServiceimpl.getBoard(id);
        return new ResponseEntity<>(findBoard, HttpStatus.OK);
    }

    @GetMapping("/board")
    public ResponseEntity<?> getBoardList(){
        return new ResponseEntity<>(boardServiceimpl.getBoardList(), HttpStatus.OK);
    }
}
