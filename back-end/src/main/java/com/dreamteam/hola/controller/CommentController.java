package com.dreamteam.hola.controller;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.dto.comment.CommentDto;
import com.dreamteam.hola.dto.comment.CommentReqDto;
import com.dreamteam.hola.exception.ErrorResponse;
import com.dreamteam.hola.service.CommentServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentServiceImpl commentService;

    // Comment 생성하기_2022_06_17_by_김우진
    @ApiOperation(value = "댓글 생성 API", notes = "로그인 한 사용자가 작성한 게시글에 댓글을 생성합니다.")
    @ApiImplicitParam(name = "CommentReqDto", value = "댓글 생성에 필요한 내용을 담은 Request Body")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "올바르지 않은 매개변수 형식입니다.", response = ErrorResponse.class),
    })
    @PostMapping("/comment")
    public ResponseEntity<?> save(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails, @Valid @RequestBody CommentReqDto commentReqDto) {
        Long memberId = principalDetails.getMemberDto().getMemberId();
        commentService.save(memberId, commentReqDto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    // Comment 수정하기_2022_06_19_by_김우진
    @ApiOperation(value = "댓글 수정 API", notes = "로그인 한 사용자가 작성한 댓글을 수정합니다.")
    @ApiImplicitParam(name = "CommentReqDto", value = "댓글 수정에 필요한 내용을 담은 Request Body")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "올바르지 않은 매개변수 형식입니다.", response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "게시글이 존재하지 않습니다.", response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "댓글에 해당되는 게시글 번호가 아닙니다.", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "존재하지 않는 댓글입니다.", response = ErrorResponse.class),
    })
    @PutMapping("/comment")
    public ResponseEntity<?> update(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,
                                    @Valid @RequestBody CommentReqDto commentDto) {
        Long memberId = principalDetails.getMemberDto().getMemberId();
        commentService.update(memberId, commentDto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 조회 API", notes = "게시글에 달려있는 댓글들을 조회합니다.")
    @ApiImplicitParam(name = "id", value = "조회할 게시글의 id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회된 게시글의 댓글 정보 List", response = CommentDto.class, responseContainer = "List"),
    })
    @GetMapping("/comment/{id}")
    public ResponseEntity<?> getComments(@PathVariable("id") Long boardId){
        return new ResponseEntity<>(commentService.getComments(boardId), HttpStatus.OK);
    }
}
