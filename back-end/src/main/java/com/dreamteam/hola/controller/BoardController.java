package com.dreamteam.hola.controller;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.dto.BoardDto;
import com.dreamteam.hola.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardController {


    private final BoardServiceImpl boardServiceimpl;

    // Board 1개 가져오기_2022_06_06_by_김우진
    @GetMapping("/board/{id}")
    public ResponseEntity<?> getBoard(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long id) {
        log.info(id + "번의 게시글 조회 API");
        Long memberId = principalDetails.getMember().getMemberId();
        BoardDto findBoard = boardServiceimpl.getBoard(memberId, id);
        return new ResponseEntity<>(findBoard, HttpStatus.OK);
    }

    // Board 전체 List 가져오기_2022_06_08_by_김우진
    @GetMapping("/boards/{recruitType}")
    public ResponseEntity<?> getBoardList(@PathVariable String recruitType) {

        System.out.println(recruitType);
        return new ResponseEntity<>(boardServiceimpl.getBoardListByRecruitType(recruitType), HttpStatus.OK);
    }

    // SkillType으로 Board 조회_2022_06_17_by_김우진
    @GetMapping("/boards")
    public ResponseEntity<?> getBoardListBySkillType(@RequestBody Map<String, List<String>> skills) {
        return new ResponseEntity<>(boardServiceimpl.getBoardListBySkillType(skills.get("skills")), HttpStatus.OK);
    }

    // 모집 마감 토글_2022_06_19_by_김우진
    @PatchMapping("/board/{id}")
    public ResponseEntity<?> updateRecruitStatus(@PathVariable Long id) {
        return new ResponseEntity<>(boardServiceimpl.updateRecruitStatus(id), HttpStatus.OK);
    }

    // Board 전체 수정_2022_06_19_by_김우진
    @PutMapping("/board/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody BoardDto BoardDto) {
        return new ResponseEntity<>(boardServiceimpl.update(id, BoardDto), HttpStatus.OK);
    }

    //Board 게시물 등록하기_2022_06_22_by_정은비
    @PostMapping("/board/register")
    public ResponseEntity<?> register(@RequestBody BoardDto boardDto) {
        boardServiceimpl.register(boardDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
