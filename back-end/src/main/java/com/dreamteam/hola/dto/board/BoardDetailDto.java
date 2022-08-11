package com.dreamteam.hola.dto.board;

import com.dreamteam.hola.dto.CommentDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@Builder
public class BoardDetailDto {
    @ApiModelProperty(value="게시글 id", example = "1")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long  id;

    @ApiModelProperty(value="작성자", example = "WeCo")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String writer;

    @ApiModelProperty(value="작성자 프로필")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String writerProfile;

    @ApiModelProperty(value="게시글 제목", example="매주 주말 오전 10시부터 6시까지 모각코 하실 분들 구합니다.")
    private String title;

    @ApiModelProperty(value="게시글 내용", example = "안녕하세요. 자세한 내용은 이메일로 보내주시면 정하겠습니다.")
    private String content;

    @ApiModelProperty(value="모집 상태", example="Y")
    private String recruitStatus;

    @ApiModelProperty(value="모집 구분", example="스터디")
    private String recruitType;

    @ApiModelProperty(value="모집 인원", example="4명")
    private String recruitCnt;

    @ApiModelProperty(value="진행 방식", example="오프라인")
    private String progressType;

    @ApiModelProperty(value="예상 기간", example="3개월")
    private String duration;

    @ApiModelProperty(value="시작 예정", example="2022-08-11")
    private Date startDate;

    @ApiModelProperty(value="연락 방법", example="카카오톡 오픈채팅")
    private String contactType;

    @ApiModelProperty(value="연락 링크", example="https://open.kakao.com/o/test223")
    private String contact;

    @ApiModelProperty(value="작성 일", example="2022-08-09")
    private Date regDate;

    @ApiModelProperty(value="댓글 수", example="3")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long commentCnt;

    @ApiModelProperty(value="사용 언어", example="[\"JPA\",\"React\",\"Spring\"]", allowEmptyValue = true)
    @Builder.Default
    private List<String> skills = new ArrayList<>();

    @ApiModelProperty(value="댓글", example="[{\"comment_id\": 3, \"board_id\": 1, \"writer\": \"Wedi\"}, {\"comment_id\": 4, \"board_id\": 1, \"writer\": \"WeCo\"}]")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<CommentDto> comments;
}
