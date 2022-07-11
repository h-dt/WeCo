package com.dreamteam.hola.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecommendedBoardDto {
    private Long  boardId;
    private String title;
}
