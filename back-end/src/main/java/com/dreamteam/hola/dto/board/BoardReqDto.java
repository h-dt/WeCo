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
public class BoardReqDto {      // update, create 용 DTO

    @Schema(description = "작성된 게시글 id",defaultValue = "1",example = "4",hidden = true)
    @JsonProperty(value = "board_id")
    private Long  id;

    @Schema(description = "작성자 id(login 한 사용자)",example = "4",hidden = true)
////    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long memberId;

    @Schema(description = "게시글 제목",example = "React, Spring으로 간단한 사이드 프로젝트 만드실 분 구합니다.",required = true)
    @NotBlank
    private String title;

    @Schema(description = "게시글 내용",example = "9월 부터 12월 까지 사이드 프로젝트 하실분 구합니다.",required = true)
    @NotBlank
    private String content;

    @Schema(description="모집 구분", example="프로젝트", required = true, allowableValues = "\"전체(null)\",\"프로젝트\",\"스터디\"")
    @NotBlank
//    @Pattern(regexp = "[프로젝트|스터디]")
    private String recruitType;    //모집 구분

    @Schema(description="모집 인원", example="6명", required = true, allowableValues = "\"인원미정\",\"1명\",\"2명\",\"3명\",\"4명\",\"5명\",\"6명\",\"7명\",\"8명\",\"9명\",\"10명 이상\"")
    @NotBlank
//    @Pattern(regexp = "[1-10]")
    private String recruitCnt;     //모집 인원

    @Schema(description="진행 방식", example="온라인", required = true, allowableValues = "\"온라인\",\"오프라인\"")
    @NotBlank
//    @Pattern(regexp="[온라인|오프라인]")
    private String progressType;

    @Schema(description="예상 기간", example="3개월", required = true, allowableValues = "\"기간미정\",\"1개월\",\"2개월\",\"3개월\",\"4개월\",\"5개월\",\"6개월\",\"장기\"")
    @NotBlank
    private String duration;        //진행 기간

    @Schema(description="시작 예정일", example="2022-09-14", required = true)
    @NotNull
//    @Pattern(regexp = "^20(\\d{2})-(\\d{2})-(\\d{2})")
    private String startDate;   //시작 예정일

    @Schema(description="연락 방법", example="이메일", required = true, allowableValues = "\"카카오톡 오픈채팅\",\"구글폼\",\"이메일\"")
    @NotBlank
//    @Pattern(regexp = "[카카오톡 오픈채팅|구글폼|이메일]")
    private String contactType;

//    @Schema(description="작성일(작성 안해도 됨)")
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    private String regDate;
//
//    @Schema(description="수정일(작성 안해도 됨)")
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    private String modDate;

    @Schema(description="연락 링크", example="test233@gmail.com", required = true)
    @NotBlank
    private String contact;

    @Schema(description="사용 언어",required = true)
//    @NotBlank
    @Builder.Default
    private List<String> skills = new ArrayList<>();

}
