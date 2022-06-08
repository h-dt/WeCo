package com.dreamteam.hola.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDetailResDto {
    private String title;
    private String content;
    private Date regDate;
    private String recruitType;
    private String recruitCnt;
    private String duration;
    private String contact;
    private String startDate;
}
