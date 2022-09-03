package com.dreamteam.hola.controller;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.dto.comment.CommentDto;
import com.dreamteam.hola.dto.comment.CommentReqDto;
import com.dreamteam.hola.exception.ErrorResponse;
import com.dreamteam.hola.service.CommentServiceImpl;
import io.swagger.annotations.*;
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

    @PostMapping("/comment")
    public ResponseEntity<?> save(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails, @Valid @RequestBody CommentReqDto commentReqDto) {
        Long memberId = principalDetails.getMemberDto().getMemberId();
        commentService.save(memberId, commentReqDto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    // Comment 수정하기_2022_06_19_by_김우진

    @PutMapping("/comment")
    public ResponseEntity<?> update(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,
                                    @Valid @RequestBody CommentReqDto commentDto) {
        Long memberId = principalDetails.getMemberDto().getMemberId();
        commentService.update(memberId, commentDto);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }


    @GetMapping("/comment/{id}")
    public ResponseEntity<?> getComments(@PathVariable("id") Long boardId){
        return new ResponseEntity<>(commentService.getComments(boardId), HttpStatus.OK);
    }


    @DeleteMapping("/comment")
    public ResponseEntity<?> deleteComment(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,@RequestBody CommentReqDto commentDto){
        Long memberId = principalDetails.getMemberDto().getMemberId();
        commentService.delete(memberId,commentDto);
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }
}
