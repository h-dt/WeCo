package com.dreamteam.hola.controller;

import com.dreamteam.hola.dto.BoardDetailDto;
import com.dreamteam.hola.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardServiceImpl boardServiceimpl;

    @GetMapping("/board/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {
        BoardDetailDto findBoard = boardServiceimpl.getBoard(id);
        return new ResponseEntity<>(findBoard, HttpStatus.OK);
    }

    @GetMapping("/boards/{recruitType}")
    public ResponseEntity<?> getBoardList(@PathVariable String recruitType){
        System.out.println(recruitType);
        return new ResponseEntity<>(boardServiceimpl.getBoardList(recruitType), HttpStatus.OK);
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
