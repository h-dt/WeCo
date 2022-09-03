package com.dreamteam.hola.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BoardHeartDto {
    private Long  id;

    private String title;

    private String writer;

    private String writerProfile;

    @Builder.Default
    private List<String> skills = new ArrayList<>();
}
