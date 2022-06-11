package com.dreamteam.hola.dto;

import com.dreamteam.hola.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDto {
    private String title;
    private String content;
    private Date regDate;
    private String recruitType;
    private String recruitCnt;
    private String duration;
    private String contact;
    private Date startDate;
}
