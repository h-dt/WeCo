package com.dreamteam.hola.domain;

import java.sql.Timestamp;

public class Board {

    private long board_id;          //게시믈 번호
    private long member_id;         //작성자 번호
    private String title;           //제목
    private String content;         // 내용
    private long view_cnt;          // 조회수
    private long recruit_status;    //모집 상태
    private Timestamp reg_date;     // 등록일
    private Timestamp mod_date;     //수정일
    private String recruit_type;    //모집 구분
    private String recruit_cnt;     //모집 인원
    private String duration;        //진행 기간
    private String contact;         //연락 방법
    private Timestamp start_date;   //시작 예정일
}
