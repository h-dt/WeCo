package com.dreamteam.hola.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class searchBoardDto {
    private String keyword;
    private String option;
}
