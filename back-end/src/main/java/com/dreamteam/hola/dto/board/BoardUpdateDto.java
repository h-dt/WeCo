package com.dreamteam.hola.dto.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@Builder
public class BoardUpdateDto {
    @Schema(description = "작성된 게시글 id",defaultValue = "1",example = "4")
    @JsonProperty(value = "board_id")
    private Long  id;

    @Schema(description = "작성자 id(login 한 사용자)",example = "4",hidden = true)
    private Long memberId;

    @Schema(description = "게시글 제목",example = "React, Spring으로 간단한 사이드 프로젝트 만드실 분 구합니다.",required = true)
    @NotBlank
    private String title;

    @Schema(description = "게시글 내용",example = "9월 부터 12월 까지 사이드 프로젝트 하실분 구합니다.",required = true)
    @NotBlank
    private String content;

    @Schema(description="모집 구분", example="스터디", required = true, allowableValues = "\"전체(null)\",\"프로젝트\",\"스터디\"")
    @NotBlank
    private String recruitType;    //모집 구분

    @Schema(description="모집 인원", example="4명", required = true, allowableValues = "\"인원미정\",\"1명\",\"2명\",\"3명\",\"4명\",\"5명\",\"6명\",\"7명\",\"8명\",\"9명\",\"10명 이상\"")
    @NotBlank
    private String recruitCnt;     //모집 인원

    @Schema(description="진행 방식", example="오프라인", required = true, allowableValues = "\"온라인\",\"오프라인\"")
    @NotBlank
    private String progressType;

    @Schema(description="예상 기간", example="3개월", required = true, allowableValues = "\"기간미정\",\"1개월\",\"2개월\",\"3개월\",\"4개월\",\"5개월\",\"6개월\",\"장기\"")
    @NotBlank
    private String duration;        //진행 기간

    @Schema(description="시작 예정일", example="2022-09-22", required = true)
    @NotNull
    private String startDate;   //시작 예정일

    @Schema(description="연락 방법", example="카카오톡 오픈채팅", required = true, allowableValues = "\"카카오톡 오픈채팅\",\"구글폼\",\"이메일\"")
    @NotBlank
    private String contactType;

    @Schema(description="연락 링크", example="test233@gmail.com", required = true)
    @NotBlank
    private String contact;

    @Schema(description="사용 언어",required = true)
    @Builder.Default
    private List<String> skills = new ArrayList<>();
}
