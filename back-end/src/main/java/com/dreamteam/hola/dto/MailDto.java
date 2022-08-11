package com.dreamteam.hola.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class MailDto {
    @ApiModelProperty(value="평가 점수", example = "4.5")
    @PositiveOrZero
    @Max(value = 5)
    private double rating;

    @ApiModelProperty(value="평가 내용", example = "WeCo 너무 맘에 들어요~")
    @NotBlank
    private String evaluateText;
}
