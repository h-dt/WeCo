package com.dreamteam.hola.controller;

import com.dreamteam.hola.config.auth.PrincipalDetails;
import com.dreamteam.hola.dto.CommentDto;
import com.dreamteam.hola.service.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentServiceImpl commentServiceImpl;

    // Comment 생성하기_2022_06_17_by_김우진
    @PostMapping("/comment")
    public ResponseEntity<?> save(@AuthenticationPrincipal PrincipalDetails principalDetails, @Valid @RequestBody CommentDto commentDto) {
        Long memberId = principalDetails.getMember().getMemberId();
        commentServiceImpl.save(memberId, commentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Comment 수정하기_2022_06_19_by_김우진
    @PutMapping("/comment/{id}")
    public ResponseEntity<?> update(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                    @PathVariable Long id,
                                    @Valid @RequestBody CommentDto commentDto) {
        Long memberId = principalDetails.getMember().getMemberId();
        commentServiceImpl.update(memberId, id, commentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
