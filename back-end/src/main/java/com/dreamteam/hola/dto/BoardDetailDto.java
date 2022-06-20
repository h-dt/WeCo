package com.dreamteam.hola.dto;

import com.dreamteam.hola.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDetailDto {
//    private String nickname;
    private String title;
    private String content;
    private Date regDate;
    private String recruitStatus;
    private String recruitType;
    private String recruitCnt;
    private String duration;
    private String contactType;
    private String contact;
    private Date startDate;
    private List<Comment> comments;
    private List<String> skills;

}
