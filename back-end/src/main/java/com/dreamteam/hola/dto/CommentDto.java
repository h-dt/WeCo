package com.dreamteam.hola.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDto {
//    private Long memeber_id;
    private Long boardId;
    private String content;
}
