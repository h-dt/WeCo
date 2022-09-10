package com.dreamteam.hola.dto.board;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@Builder
@Schema(description = "Board 상세정보")
public class BoardDetailDto {
    @Parameter(description = "게시글 id",example = "1")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long  id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String writer;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String writerProfile;

    private String title;

    private String content;

    private String recruitStatus;

    private String recruitType;

    private String recruitCnt;

    private String progressType;

    private String duration;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Seoul")
    private String startDate;

    private String contactType;

    private String contact;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Seoul")
    private String regDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long commentCnt;

    @Builder.Default
    private List<String> skills = new ArrayList<>();
}
