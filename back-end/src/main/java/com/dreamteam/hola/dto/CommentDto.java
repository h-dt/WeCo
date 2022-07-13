package com.dreamteam.hola.dto;

import com.dreamteam.hola.domain.BigComment;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonPropertyOrder({"commentId", "boardId", "writer", "content", "cDepth",
        "cGroup", "bigCommentCnt", "bigComments"})
public class CommentDto {
//    private Long memeber_id;

    @JsonProperty(value = "comment_id", access = JsonProperty.Access.READ_ONLY)
    private Long commentId;

    @NotNull
    @Positive
    @JsonProperty(value = "board_id", access = JsonProperty.Access.READ_ONLY)
    private Long boardId;

    @Positive
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String writer;

    @NotBlank
    private String content;

    @JsonProperty(value = "c_depth")
    private boolean cDepth;        // 일반 댓글 0, 대댓글 1

    @Min(value = 1)
    @JsonProperty(value = "c_group")
    private Long cGroup;        // 대댓글의 경우 모댓글의 id 값

    private Long bigCommentCnt;

    private List<BigComment> bigComments;
}
