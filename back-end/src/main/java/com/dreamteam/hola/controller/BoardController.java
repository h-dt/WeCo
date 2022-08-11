package com.dreamteam.hola.controller;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.dto.board.*;
import com.dreamteam.hola.exception.ErrorResponse;
import com.dreamteam.hola.service.BoardServiceImpl;
import com.dreamteam.hola.service.HeartServiceImpl;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;


@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardServiceImpl boardServiceimpl;
    private final HeartServiceImpl heartServiceImpl;

    /*********************************** GET API ***********************************/

    // Board 1개 가져오기_2022_06_06_by_김우진
    @ApiOperation(value = "게시글 조회 API", notes = "게시글 id를 통해서 게시글 정보를 조회합니다.")
    @ApiParam(name = "게시글 id", value = "조회할 게시글 id", required = true, type = "path")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회된 게시글 상세 정보", response = BoardDetailDto.class),
            @ApiResponse(code = 500, message = "존재하지 않는 글입니다.", response = ErrorResponse.class)
    })
    @GetMapping("/board/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {

        log.info(id + "번의 게시글 조회 API");

        return new ResponseEntity<>(boardServiceimpl.getBoard(id), HttpStatus.OK);
    }

    @ApiOperation(value = "내가 작성한 게시글 조회 API", notes = "로그인한 사용자의 정보로 그 Member가 작성한 게시글 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Member가 작성한 게시글 List", response = BoardListDto.class, responseContainer = "List"),
    })
    // 내가 작성한 게시글 보기_2022_07_12_by_김우진
    @GetMapping("/my-boards")
    public ResponseEntity<?> getMyBoards(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails){
        log.info("내가 작성한 게시글 조회 API");
        Long memberId = principalDetails.getMemberDto().getMemberId();
        return new ResponseEntity<>(boardServiceimpl.getMyBoards(memberId), HttpStatus.OK);
    }

    /*********************************** POST API ***********************************/

    // Board 전체 List 가져오기_2022_06_08_by_김우진
    @ApiOperation(value = "조건에 맞는 전체 게시글 조회 API", notes = "모집구분(전체/프로젝트/스터디), 기술스택, 모집상태에 따라서 게시글 조회")
    @ApiImplicitParam(name = "BoardReqDto", value = "조회에 필요한 조건을 담는 Request Body")
    @ApiResponses({
            @ApiResponse(code = 200, message = "결과에 맞게 조회된 게시글 List", response = BoardListDto.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "올바르지 않은 매개변수 형식입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "존재하지 않는 글입니다.", response = ErrorResponse.class)
    })
    @PostMapping("/boards")
    public ResponseEntity<?> getBoards(@Valid @RequestBody BoardFilterDto boardReqDto) {
        log.info("조건에 맞는 board 전체 게시글 조회 API");
        return new ResponseEntity<>(boardServiceimpl.getBoards(boardReqDto), HttpStatus.OK);
    }

    //Board 게시물 등록하기_2022_06_22_by_정은비
    @ApiOperation(value = "게시글 등록 API", notes = "로그인 한 사용자를 작성자로 게시글 작성합니다.")
    @ApiImplicitParam(name = "BoardReqDto", value = "작성에 필요한 내용을 담는 Request Body")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "올바르지 않은 매개변수 형식입니다.", response = ErrorResponse.class),
    })
    @PostMapping("/board")
    public ResponseEntity<?> save(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,
                                      @Valid @RequestBody BoardReqDto boardDto) {
        Long memberId = principalDetails.getMemberDto().getMemberId();
        boardServiceimpl.save(memberId, boardDto);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /*********************************** PATCH API ***********************************/

    // 모집 마감 토글_2022_06_19_by_김우진
    @ApiOperation(value = "게시글 모집 마감 토클 API", notes = "모집 활성/모집 비활성 토클")
    @ApiImplicitParam(name = "게시글 id", value = "모집 상태를 변경할 게시글 id", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "존재하지 않는 글입니다.", response = ErrorResponse.class)
    })
    @PatchMapping("/board/{id}")
    public ResponseEntity<?> updateRecruitStatus(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long id) {
        log.info("모집 마감 상태 변경 API");
        Long memberId = principalDetails.getMemberDto().getMemberId();
        return new ResponseEntity<>(boardServiceimpl.updateRecruitStatus(memberId, id), HttpStatus.OK);
    }

    /*********************************** PUT API ***********************************/

    // Board 전체 수정_2022_06_19_by_김우진 - skilltype 추가 해야 함
    @ApiOperation(value = "게시글 수정 API", notes = "로그인 한 사용자가 작성한 게시글을 수정합니다.")
    @ApiImplicitParam(name = "BoardReqDto", value = "수정에 필요한 내용을 담는 Request Body")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "올바르지 않은 매개변수 형식입니다.", response = ErrorResponse.class),
    })
    @PutMapping("/board/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody BoardReqDto boardReqDto) {
        boardServiceimpl.update(id, boardReqDto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /*********************************** DELETE API ***********************************/




    // 추천 게시물 List 가져오기_2022_07_11_by_정은비
    @GetMapping("/board/recommend")
    public ResponseEntity<?> getRecommendedBoardList() {
        return new ResponseEntity<>(boardServiceimpl.getRecommendedBoardList(), HttpStatus.OK);
    }

    @PostMapping("/heart/{boardId}")
    public ResponseEntity<?> addHeart(@AuthenticationPrincipal PrincipalDetails principalDetails,@PathVariable Long boardId){

        boolean result = false;
        if(principalDetails != null){
            result = heartServiceImpl.save(boardId,principalDetails.getMemberDto().getMemberId());
        }
        return result ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/heart/{boardId}")
    public ResponseEntity<?> deleteHeart(@AuthenticationPrincipal PrincipalDetails principalDetails,@PathVariable Long boardId){
        Long memberId = principalDetails.getMemberDto().getMemberId();
        heartServiceImpl.delete(boardId,memberId);

        return new ResponseEntity<>("좋아요 삭제",HttpStatus.OK);

    }
}
