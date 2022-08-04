package com.dreamteam.hola.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Heart {

    private Long heartId;
    private Long boardId;
    private Long memberId;

}
