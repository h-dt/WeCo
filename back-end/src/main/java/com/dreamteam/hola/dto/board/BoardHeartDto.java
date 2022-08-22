package com.dreamteam.hola.dto.board;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value="게시글 id", example = "1")
    private Long  id;

    @ApiModelProperty(value="게시글 제목", example="매주 주말 오전 10시부터 6시까지 모각코 하실 분들 구합니다.")
    private String title;

    @ApiModelProperty(value="작성자", example = "WeCo")
    private String writer;

    @ApiModelProperty(value="작성자 프로필")
    private String writerProfile;

    @ApiModelProperty(value="기술 스택", example="[\"JPA\",\"React\",\"Spring\"]")
    @Builder.Default
    private List<String> skills = new ArrayList<>();
}
