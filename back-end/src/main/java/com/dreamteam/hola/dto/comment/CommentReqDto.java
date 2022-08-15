package com.dreamteam.hola.dto.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class CommentReqDto {
//    private Long memeber_id;

    @ApiModelProperty(value="생성된 댓글 id", example="17")
    private Long commentId;

    @ApiModelProperty(value="댓글이 생성될 게시글 id", example="4", required = true)
    @NotNull
    @Positive
    private Long boardId;

    @ApiModelProperty(value="댓글 내용", example="마음에 드는 게시글이네요. 참석하고 싶습니다.", required = true)
    @NotBlank
    private String content;

    @ApiModelProperty(value="작성일(작성 안해도 됨)")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String regDate;

    @ApiModelProperty(value="수정일(작성 안해도 됨)")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String modDate;
}
