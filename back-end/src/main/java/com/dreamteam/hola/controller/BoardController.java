package com.dreamteam.hola.controller;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.dto.board.BoardDetailDto;
import com.dreamteam.hola.dto.board.BoardFilterDto;
import com.dreamteam.hola.dto.board.BoardReqDto;
import com.dreamteam.hola.exception.ErrorResponse;
import com.dreamteam.hola.service.BoardServiceImpl;
import com.dreamteam.hola.service.HeartServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary= "게시글 조회 API",description = "게시글 id를 통해서 게시글 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회된 게시글 상세 정보",content = @Content(schema = @Schema(implementation = BoardDetailDto.class))),
            @ApiResponse(responseCode = "500", description = "존재하지 않는 글입니다.", content =@Content(schema=@Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/board/{id}")
    public ResponseEntity<?> getBoard(@Parameter(name = "id", description = "조회할 게시글 id",required = true, example = "1",in= ParameterIn.PATH) @PathVariable("id") Long id) {

        log.info(id + "번의 게시글 조회 API");

        return new ResponseEntity<>(boardServiceimpl.getBoard(id), HttpStatus.OK);
    }


    // 내가 작성한 게시글 보기_2022_07_12_by_김우진
    @GetMapping("/my-boards")
    public ResponseEntity<?> getMyBoards(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails){
        log.info("내가 작성한 게시글 조회 API");
        Long memberId = principalDetails.getMemberDto().getMemberId();
        return new ResponseEntity<>(boardServiceimpl.getMyBoards(memberId), HttpStatus.OK);
    }

    /*********************************** POST API ***********************************/

    // Board 전체 List 가져오기_2022_06_08_by_김우진

    @PostMapping("/boards")
    public ResponseEntity<?> getBoards(@Valid @RequestBody BoardFilterDto boardReqDto) {
        log.info("조건에 맞는 board 전체 게시글 조회 API");
        return new ResponseEntity<>(boardServiceimpl.getBoards(boardReqDto), HttpStatus.OK);
    }

    //Board 게시물 등록하기_2022_06_22_by_정은비

    @Operation(summary = "게시글 등록 API",description = "로그인 한 사용자를 작성자로 게시글을 작성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "올바르지 않은 매개변수 형식입니다.",content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/board")
    public ResponseEntity<?> save(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                  @Valid @RequestBody BoardReqDto boardDto) {
        Long memberId = principalDetails.getMemberDto().getMemberId();
        boardServiceimpl.save(memberId, boardDto);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /*********************************** PATCH API ***********************************/

    // 모집 마감 토글_2022_06_19_by_김우진

    @PatchMapping("/board/{id}")
    public ResponseEntity<?> updateRecruitStatus(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long id) {
        log.info("모집 마감 상태 변경 API");
        Long memberId = principalDetails.getMemberDto().getMemberId();
        boardServiceimpl.updateRecruitStatus(memberId, id);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /*********************************** PUT API ***********************************/

    // Board 전체 수정_2022_06_19_by_김우진 - skilltype 추가 해야 함

    @PutMapping("/board")
    public ResponseEntity<?> update(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails, @Valid @RequestBody BoardReqDto boardReqDto) {
        Long memberId = principalDetails.getMemberDto().getMemberId();
        boardServiceimpl.update(memberId, boardReqDto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    //Board 게시글 클라이언트 에게 보이지 않게만 삭제처리

    @DeleteMapping("/board/{id}")
    public ResponseEntity<?> delete(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,@PathVariable Long id){
        Long memberId = principalDetails.getMemberDto().getMemberId();
        boardServiceimpl.delete(memberId,id);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /*********************************** DELETE API ***********************************/




    // 추천 게시물 List 가져오기_2022_07_11_by_정은비


    @GetMapping("/board/recommend")
    public ResponseEntity<?> getRecommendedBoardList() {
        return new ResponseEntity<>(boardServiceimpl.getRecommendedBoardList(), HttpStatus.OK);
    }


    @PostMapping("/heart/{id}")
    public ResponseEntity<?> addHeart(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,@PathVariable("id") Long boardId){
        heartServiceImpl.save(boardId,principalDetails.getMemberDto().getMemberId());
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }


    @DeleteMapping("/heart/{id}")
    public ResponseEntity<?> deleteHeart(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,@PathVariable("id") Long boardId){

        boolean result = false;
        if(principalDetails != null){
            log.info("1 : {}",result);
            result = heartServiceImpl.delete(boardId,principalDetails.getMemberDto().getMemberId());
            log.info("2 : {}",String.valueOf(result));
        }
        log.info("3 : {}",String.valueOf(result));

        return result ? new ResponseEntity<>(result,HttpStatus.OK) : new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);

    }


    @GetMapping("/myheart")
    public ResponseEntity<?> showMyHeart (@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails){
        log.info("id={}",principalDetails.getMemberDto().getMemberId());
        Long memberId = principalDetails.getMemberDto().getMemberId();
        return new ResponseEntity<>(heartServiceImpl.HeartList(memberId),HttpStatus.OK);
    }
}
