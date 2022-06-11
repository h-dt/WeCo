package com.dreamteam.hola.controller;

import com.dreamteam.hola.domain.Board;
import com.dreamteam.hola.domain.Member;
import com.dreamteam.hola.dto.BoardDetailResDto;
import com.dreamteam.hola.service.BoardService;
import com.dreamteam.hola.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardServiceImpl boardServiceimpl;

    @GetMapping("/board/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {
        BoardDetailResDto findBoard = boardServiceimpl.getBoard(id);
        return new ResponseEntity<>(findBoard, HttpStatus.OK);
    }

    @GetMapping("/board")
    public ResponseEntity<?> getBoardList() {
        return new ResponseEntity<>(boardServiceimpl.getBoardList(), HttpStatus.OK);
    }

    @PostMapping("/board/register")
    public ResponseEntity<?> register(@RequestBody BoardDetailResDto dto) {
//        Board.builder()


        try {
                int rowCnt = boardServiceimpl.register(dto);
            if (rowCnt != 1) {
                throw new Exception();
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
