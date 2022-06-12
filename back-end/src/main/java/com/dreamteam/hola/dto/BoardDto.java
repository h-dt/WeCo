package com.dreamteam.hola.dto;

import com.dreamteam.hola.domain.Comment;
import com.dreamteam.hola.domain.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
    private List<Comment> comments;
    private List<Skill> skills;
}
