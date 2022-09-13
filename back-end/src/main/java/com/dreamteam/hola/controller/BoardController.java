package com.dreamteam.hola.controller;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.dto.board.*;
import com.dreamteam.hola.exception.ErrorResponse;
import com.dreamteam.hola.service.BoardServiceImpl;
import com.dreamteam.hola.service.HeartServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
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
    @Operation(summary = "내가 작성한 게시글 조회 API", description = "로그인한 사용자의 정보로 작성한 게시글 조회합니다.")
    @ApiResponses({
           @ApiResponse(responseCode = "200", description = "Member가 작성한 게시글 List", content = @Content(schema = @Schema(implementation = BoardListDto.class)))
    })
    @GetMapping("/my-boards")
    public ResponseEntity<?> getMyBoards(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails){
        log.info("내가 작성한 게시글 조회 API");
        Long memberId = principalDetails.getMemberDto().getMemberId();
        return new ResponseEntity<>(boardServiceimpl.getMyBoards(memberId), HttpStatus.OK);
    }

    // 추천 게시물 List 가져오기_2022_07_11_by_정은비
    @Operation(summary = "추천 게시글 API",description = "댓글수,조회수,등록날짜에 따른 점수로 10개의 게시글 추천게시글 목록")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "추천 게시글 List",content = @Content(schema = @Schema(implementation = RecommendedBoardDto.class)))
    })
    @GetMapping("/board/recommend")
    public ResponseEntity<?> getRecommendedBoardList() {
        return new ResponseEntity<>(boardServiceimpl.getRecommendedBoardList(), HttpStatus.OK);
    }

    @Operation(summary = "Heart를 누른 게시글 List",description = "해당 User의 좋아요한 게시글 목록")
    @GetMapping("/myheart")
    public ResponseEntity<?> showMyHeart (@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails){
        log.info("id={}",principalDetails.getMemberDto().getMemberId());
        Long memberId = principalDetails.getMemberDto().getMemberId();
        return new ResponseEntity<>(heartServiceImpl.HeartList(memberId),HttpStatus.OK);
    }
    /*********************************** POST API ***********************************/

    // Board 전체 List 가져오기_2022_06_08_by_김우진
    @Operation(summary = "조건에 맞는 전체 게시글 조회 API", description = "모집구분(전체/프로젝트/스터디), 기술스택, 모집상태에 따라서 게시글 조회")
    @ApiImplicitParam(name = "BoardReqDto", value = "조회에 필요한 조건을 담는 Request Body")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "결과에 맞게 조회된 게시글 List", content = @Content(schema = @Schema(implementation = BoardListDto.class))),
            @ApiResponse(responseCode = "400", description = "올바르지 않은 매개변수 형식입니다.",content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "존재하지 않는 글입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/boards")
    public ResponseEntity<?> getBoards(@Valid @RequestBody BoardFilterDto boardReqDto) {
        log.info("조건에 맞는 board 전체 게시글 조회 API");
        return new ResponseEntity<>(boardServiceimpl.getBoards(boardReqDto), HttpStatus.OK);
    }

    //Board 게시물 등록하기_2022_06_22_by_정은비

    @Operation(summary = "게시글 등록 API",description = "로그인 한 사용자를 작성자로 게시글을 작성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "올바르지 않은 매개변수 형식입니다.",content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/board")
    public ResponseEntity<?> save(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                  @Parameter @Valid @RequestBody BoardReqDto boardDto) {
        Long memberId = principalDetails.getMemberDto().getMemberId();
        boardServiceimpl.save(memberId, boardDto);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @Operation(summary ="해당 Board의 heart를 추가 (사용자당 하나의 heart만 가능)",description = "좋아요 활성화")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "true"),
            @ApiResponse(responseCode = "400", description = "false")
    })
    @PostMapping("/heart/{id}")
    public ResponseEntity<?> addHeart(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails, @Parameter(name = "id", description = "포스트 대상 pK",required = true,example = "1",in = ParameterIn.PATH)@PathVariable("id") Long boardId){
        heartServiceImpl.save(boardId,principalDetails.getMemberDto().getMemberId());
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }

    /*********************************** PATCH API ***********************************/

    // 모집 마감 토글_2022_06_19_by_김우진
    @Operation(summary = "게시글 모집 마감 토클 API", description = "모집 활성/모집 비활성 토클")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "존재하지 않는 글입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping("/board/{id}")
    public ResponseEntity<?> updateRecruitStatus(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                 @Parameter(name = "id",description = "모집 수정할 게시글 id",required = true, example = "1",in= ParameterIn.PATH)@PathVariable Long id) {
        log.info("모집 마감 상태 변경 API");
        Long memberId = principalDetails.getMemberDto().getMemberId();
        boardServiceimpl.updateRecruitStatus(memberId, id);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /*********************************** PUT API ***********************************/

    // Board 전체 수정_2022_06_19_by_김우진 - skilltype 추가 해야 함

    @Operation(summary = "게시글 수정 API", description = "로그인 한 사용자가 작성한 게시글을 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "올바르지 않은 매개변수 형식입니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/board")
    public ResponseEntity<?> update(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,
                                    @Parameter @Valid @RequestBody BoardUpdateDto boardReqDto) {
        log.info("게시글 수정 API");
        Long memberId = principalDetails.getMemberDto().getMemberId();
        boardServiceimpl.update(memberId, boardReqDto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }


    /*********************************** DELETE API ***********************************/

    //Board 게시글 클라이언트 에게 보이지 않게만 삭제처리
    @Operation(summary = "게시글 삭제 API", description = "로그인 한 사용자가 작성한 게시글을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 글입니다.",content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "해당 게시글에 권한이 없습니다.",content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/board/{id}")
    public ResponseEntity<?> delete(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,@Parameter(name="id",description = "삭제할 게시물 id",required = true, example = "1",in= ParameterIn.PATH)@PathVariable Long id){
        Long memberId = principalDetails.getMemberDto().getMemberId();
        boardServiceimpl.delete(memberId,id);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @Operation(summary = "해당 Board의 Heart 삭제",description = "좋아요 비활성화")
    @ApiImplicitParam(name = "id", value = "포스트 대상 PK",dataType = "Long")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "true"),
            @ApiResponse(responseCode = "400", description = "false")
    })
    @DeleteMapping("/heart/{id}")
    public ResponseEntity<?> deleteHeart(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,
                                         @Parameter(name = "id",description = "포스트 대상 PK",required = true,example = "1",in = ParameterIn.PATH)@PathVariable("id") Long boardId){

        boolean result = false;
        if(principalDetails != null){
            log.info("1 : {}",result);
            result = heartServiceImpl.delete(boardId,principalDetails.getMemberDto().getMemberId());
            log.info("2 : {}",String.valueOf(result));
        }
        log.info("3 : {}",String.valueOf(result));

        return result ? new ResponseEntity<>(result,HttpStatus.OK) : new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);

    }



}
