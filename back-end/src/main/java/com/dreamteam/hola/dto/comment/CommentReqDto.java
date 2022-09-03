package com.dreamteam.hola.dto.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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

    @Positive
    private Long commentId;

    @NotNull
    @Positive
    private Long boardId;

    @NotBlank
    private String content;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String regDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String modDate;
}
