package com.dreamteam.hola.dto.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
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
public class BoardListDto {

    @ApiModelProperty(value="게시글 id", example = "1")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long  id;

    @ApiModelProperty(value="작성자", example = "WeCo")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String writer;

    @ApiModelProperty(value="작성자 프로필")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String writerProfile;

    @ApiModelProperty(value="게시글 제목", example="매주 주말 오전 10시부터 6시까지 모각코 하실 분들 구합니다.")
    private String title;

    @ApiModelProperty(value="모집 상태", example="Y")
    private String recruitStatus;

    @ApiModelProperty(value="모집 구분", example="스터디")
    private String recruitType;

    @ApiModelProperty(value="모집 인원", example="4명")
    private String recruitCnt;

    @ApiModelProperty(value="진행 방식", example="오프라인")
    private String progressType;
    
    @ApiModelProperty(value="진행 기간", example="3개월")
    private String duration;

    @ApiModelProperty(value="시작 예정일", example="2022-08-11")
    private String startDate;

    @ApiModelProperty(value="조회 수", example="29")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long viewCnt;

    @ApiModelProperty(value="댓글 수", example="3")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long commentCnt;

    @ApiModelProperty(value="기술 스택", example="[\"JPA\",\"React\",\"Spring\"]")
    @Builder.Default
    private List<String> skills = new ArrayList<>();


}
