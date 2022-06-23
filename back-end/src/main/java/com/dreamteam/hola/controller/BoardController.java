package com.dreamteam.hola.controller;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.dto.BoardDetailDto;
import com.dreamteam.hola.dto.BoardDto;
import com.dreamteam.hola.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



import java.util.Arrays;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController
public class BoardController {


    private final BoardServiceImpl boardServiceimpl;

    // Board 1개 가져오기_2022_06_06_by_김우진
    @GetMapping("/board/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {
        BoardDetailDto findBoard = boardServiceimpl.getBoard(id);
        return new ResponseEntity<>(findBoard, HttpStatus.OK);
    }

    // Board 전체 List 가져오기_2022_06_08_by_김우진
    @GetMapping("/boards/{recruitType}")
    public ResponseEntity<?> getBoardList(@PathVariable String recruitType) {
        System.out.println(recruitType);
        return new ResponseEntity<>(boardServiceimpl.getBoardListByRecruitType(recruitType), HttpStatus.OK);
    }

    @GetMapping("/boards")
    public ResponseEntity<?> getBoardListBySkillType(@RequestBody Map<String,List<String>> skills) {
        return new ResponseEntity<>(boardServiceimpl.getBoardListBySKillType(skills.get("skills")), HttpStatus.OK);
    }

    @PostMapping("/board/register")
    public ResponseEntity<?> register(@RequestBody BoardDto dto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println(principalDetails.getMember().toString());

        boardServiceimpl.register(dto);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
