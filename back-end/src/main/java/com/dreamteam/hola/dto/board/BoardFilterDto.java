package com.dreamteam.hola.dto.board;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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

    private String recruitType;

    @NotNull
    @Pattern(regexp = "[Y|N]", message = "Y 혹은 N 이어야 합니다.")
    private String recruitStatus;

    private List<String> skills;
}
