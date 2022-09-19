package com.dreamteam.hola.controller;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.dto.comment.CommentReqDto;
import com.dreamteam.hola.dto.comment.CommentResDto;
import com.dreamteam.hola.dto.comment.CommentUpdateDto;
import com.dreamteam.hola.exception.ErrorResponse;
import com.dreamteam.hola.service.CommentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@RequiredArgsConstructor
@RestController
@Slf4j
public class CommentController {
    private final CommentServiceImpl commentService;

    // Comment 생성하기_2022_06_17_by_김우진
    @Operation(summary = "댓글 생성 API", description = "로그인 한 사용자가 작성한 게시글에 댓글을 생성합니다.")
//    @ApiImplicitParam(name = "CommentReqDto", value = "댓글 생성에 필요한 내용을 담은 Request Body")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "올바르지 않은 매개변수 형식입니다.",content =@Content(schema=@Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/comment")
    public ResponseEntity<?> save(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails, @Parameter @Valid @RequestBody CommentReqDto commentReqDto) {
        Long memberId = principalDetails.getMemberDto().getMemberId();
        commentService.save(memberId, commentReqDto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    // Comment 수정하기_2022_06_19_by_김우진
    @Operation(summary = "댓글 수정 API", description = "로그인 한 사용자가 작성한 댓글을 수정합니다.")
//    @ApiImplicitParam(name = "CommentReqDto", value = "댓글 수정에 필요한 내용을 담은 Request Body")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "올바르지 않은 매개변수 형식입니다.",content =@Content(schema=@Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "게시글이 존재하지 않습니다.",content =@Content(schema=@Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "댓글에 해당되는 게시글 번호가 아닙니다.",content =@Content(schema=@Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "존재하지 않는 댓글입니다." ,content =@Content(schema=@Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/comment")
    public ResponseEntity<?> update(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,
                                    @Parameter @Valid @RequestBody CommentUpdateDto commentDto) {

        Long memberId = principalDetails.getMemberDto().getMemberId();
        commentService.update(memberId, commentDto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @Operation(summary = "댓글 조회 API", description = "게시글에 달려있는 댓글들을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회된 게시글의 댓글 정보 List",content =@Content(schema=@Schema(implementation = CommentResDto.class)))
    })
    @GetMapping("/comment/{id}")
    public ResponseEntity<?> getComments(@Parameter(name = "id",example = "1",required = true,description = "조회할 게시글의 id")@PathVariable("id") Long boardId){
        return new ResponseEntity<>(commentService.getComments(boardId), HttpStatus.OK);
    }

    @Operation(summary = "댓글 삭제 API",description = "게시글에 달려있는 댓귿들을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "게시글이 존재하지 않습니다.", content =@Content(schema=@Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "댓글에 해당되는 게시글 번호가 아닙니다.", content =@Content(schema=@Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "존재하지 않는 댓글입니다.", content =@Content(schema=@Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,
                                           @Parameter(name = "id" , example = "1",required = true,description = "삭제할 댓글의 id") @PathVariable("id") Long commentId){
        Long memberId = principalDetails.getMemberDto().getMemberId();
        commentService.delete(memberId,commentId);
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }
}
