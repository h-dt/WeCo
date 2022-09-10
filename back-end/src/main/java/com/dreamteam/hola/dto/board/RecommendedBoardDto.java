package com.dreamteam.hola.dto.board;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description="추천 게시글 id", example = "1")
    private Long  boardId;
    @Schema(description="추천 게시물 제목", example="추천 게시물 제목 예시입니다.")
    private String title;
}
