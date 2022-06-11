package com.dreamteam.hola.domain;

import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Board {

    private Long boardId;          //게시믈 번호
//    private Long memberId;         //작성자 번호
    private Long nickname;         // 닉네임
    private String title;           //제목
    private String content;         // 내용
    private Long viewCnt;          // 조회수
    private String recruitStatus;    //모집 상태
    private LocalDate regDate;     // 등록일
    private LocalDate modDate;     //수정일
    private String recruitType;    //모집 구분
    private String recruitCnt;     //모집 인원
    private String duration;        //진행 기간
    private String contact;         //연락 방법
    private LocalDate startDate;   //시작 예정일
}
