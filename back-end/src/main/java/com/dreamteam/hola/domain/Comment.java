package com.dreamteam.hola.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Comment {
    private Long commentId;
    private Long boardId;
    private String content;
    private Date regDate;
    private Date modDate;
}
