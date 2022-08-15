package com.dreamteam.hola.dto.comment;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"commentId", "boardId", "writer", "content"})
@Data
public class CommentDto {
    private Long commentId;

    private Long boardId;

    private String writer;

    private String content;
}
