package com.dreamteam.hola.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardSkill {
    private Long boardSkillId;
    private Long boardId;
    private Long skillId;
}
