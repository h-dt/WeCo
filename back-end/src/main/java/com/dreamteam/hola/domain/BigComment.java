package com.dreamteam.hola.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BigComment {
    private Long commentId;
    //    private Long memberId;
    private Long boardId;
    private String content;
    private Date regDate;
}
