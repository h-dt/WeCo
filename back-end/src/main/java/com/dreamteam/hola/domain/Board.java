package com.dreamteam.hola.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Board {

    private Long boardId;          //게시믈 번호
    private Long memberId;         //작성자 번호
    private String title;           //제목
    private String content;         // 내용
    private Long viewCnt;          // 조회수
    private Long recruitStatus;    //모집 상태
    private Timestamp regDate;     // 등록일
    private Timestamp modDate;     //수정일
    private String recruitType;    //모집 구분
    private String recruitCnt;     //모집 인원
    private String duration;        //진행 기간
    private String contact;         //연락 방법
    private Timestamp startDate;   //시작 예정일
}
