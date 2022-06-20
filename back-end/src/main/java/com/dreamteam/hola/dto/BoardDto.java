package com.dreamteam.hola.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDto {

    private Long  id;
//    private String nickname;         //작성자 번호
    private String title;           //제목
    private Long viewCnt;          // 조회수
    private String recruitStatus;    //모집 상태
    private String recruitType;    //모집 구분
    private String recruitCnt;     //모집 인원
    private String duration;        //진행 기간
    private Date startDate;   //시작 예정일
    private int commentCnt;
    private List<String> skills;


}
