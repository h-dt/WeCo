package com.dreamteam.hola.dto.board;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class BoardFilterDto {

    @ApiModelProperty(name = "recruitType", value = "모집구분", dataType = "String", notes = "조회할 게시글에 설정된 모집구분", allowableValues = "\"전체(null)\",\"프로젝트\",\"스터디\"", example = "프로젝트")
    private String recruitType;

    @ApiModelProperty(name = "recruitStatus", value = "모집상태", dataType = "String", notes = "조회할 게시글에 설정된 모집상태", allowableValues = "\"Y\",\"N\"", example = "Y", required = true)
    @NotNull
    @Pattern(regexp = "[Y|N]", message = "Y 혹은 N 이어야 합니다.")
    private String recruitStatus;

    @ApiModelProperty(name = "skills", value = "기술스택", dataType = "java.util.List<String>", notes = "조회할 게시글에 들어간 기술 스택 목록", example = "[\"JPA\",\"React\",\"Spring\"]")
    private List<String> skills;
}
