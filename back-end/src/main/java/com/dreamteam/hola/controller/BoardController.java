package com.dreamteam.hola.controller;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.dto.BoardDto;
import com.dreamteam.hola.dto.BoardReqDto;
import com.dreamteam.hola.dto.searchBoardDto;
import com.dreamteam.hola.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardServiceImpl boardServiceimpl;

    // Board 1개 가져오기_2022_06_06_by_김우진
    @GetMapping("/board/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {
        log.info(id + "번의 게시글 조회 API");
        return new ResponseEntity<>(boardServiceimpl.getBoard(id), HttpStatus.OK);
    }

    // Board 전체 List 가져오기_2022_06_08_by_김우진
    @GetMapping("/boards")
    public ResponseEntity<?> getBoards(@RequestBody BoardReqDto boardReqDto) {
        log.info("조건에 맞는 board 전체 게시글 조회 API");
        return new ResponseEntity<>(boardServiceimpl.getBoards(boardReqDto), HttpStatus.OK);
    }

    // 모집 마감 토글_2022_06_19_by_김우진
    @PatchMapping("/board/{id}")
    public ResponseEntity<?> updateRecruitStatus(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long id) {
        log.info("모집 마감 상태 변경 API");
        Long memberId = principalDetails.getMemberDto().getMemberId();
        return new ResponseEntity<>(boardServiceimpl.updateRecruitStatus(memberId, id), HttpStatus.OK);
    }

    // Board 전체 수정_2022_06_19_by_김우진 - skilltype 추가 해야 함
    @PutMapping("/board/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody BoardDto BoardDto) {
        return new ResponseEntity<>(boardServiceimpl.update(id, BoardDto), HttpStatus.OK);
    }

    //Board 게시물 등록하기_2022_06_22_by_정은비
    @PostMapping("/board/register")
    public ResponseEntity<?> register(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody BoardDto boardDto) {
        Long memberId = principalDetails.getMemberDto().getMemberId();
        log.info("게시물 등록 API");
        boardServiceimpl.register(memberId, boardDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 추천 게시물 List 가져오기_2022_07_11_by_정은비
    @GetMapping("/board/recommend")
    public ResponseEntity<?> getRecommendedBoardList() {
        return new ResponseEntity<>(boardServiceimpl.getRecommendedBoardList(), HttpStatus.OK);
    }

    // 내가 작성한 게시글 보기_2022_07_12_by_김우진
    @GetMapping("/my-boards")
    public ResponseEntity<?> getMyBoards(@AuthenticationPrincipal PrincipalDetails principalDetails){
        log.info("내가 작성한 게시글 조회 API");
        Long memberId = principalDetails.getMemberDto().getMemberId();
        return new ResponseEntity<>(boardServiceimpl.getMyBoards(memberId), HttpStatus.OK);
    }

    // 검색_2022_07_21_by_정은비
    @GetMapping("/search")
    public ResponseEntity<?> searchBoards(@RequestBody searchBoardDto searchBoardDto){
        log.info("board 검색 API");
        return new ResponseEntity<>(boardServiceimpl.searchBoards(searchBoardDto), HttpStatus.OK);
    }
}
