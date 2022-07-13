package com.dreamteam.hola.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class BoardDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long  id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String writer;         //작성자 번호

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String writerProfile;

    private String title;           //제목
    private String content;
    private String recruitStatus;    //모집 상태
    private String recruitType;    //모집 구분
    private String recruitCnt;     //모집 인원
    private String progressType;
    private String duration;        //진행 기간
    private Date startDate;   //시작 예정일
    private String contactType;
    private String contact;
    private Date regDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long viewCnt;          // 조회수

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long commentCnt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<String> skills;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<CommentDto> comments;
}