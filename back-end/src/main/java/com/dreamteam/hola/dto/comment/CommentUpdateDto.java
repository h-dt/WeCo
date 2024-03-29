package com.dreamteam.hola.dto.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class CommentUpdateDto {

    @Schema(description = "생성된 댓글 id ",example = "46",required = true)
    @Positive
    private Long commentId;

    @Schema(description = "댓글이 생성될 게시글 id",example = "1",required = true)
    @NotNull
    @Positive
    private Long boardId;

    @Schema(description = "댓글 내용",example = "마음에 드는 게시글입니다! 참석하고 싶습니다!",required = true)
    @NotBlank
    private String content;

    @Schema(hidden = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Seoul")
    private LocalDateTime regDate;

    @Schema(hidden = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Seoul")
    private LocalDateTime modDate;
}
