package com.dreamteam.hola.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Data
public class CommentDto {
//    private Long memeber_id;

    private Long commentId;

    @NotNull
    @Positive
    @JsonProperty(value = "board_id")
    private Long boardId;

    @NotBlank
    private String content;
}
