package com.dreamteam.hola.dto.board;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@Builder
public class RecommendedBoardDto {

    @ApiModelProperty(value="추천 게시글 id", example = "1")
    private Long  boardId;
    @ApiModelProperty(value="추천 게시물 제목", example="추천 게시물 제목 예시입니다.")
    private String title;
}
