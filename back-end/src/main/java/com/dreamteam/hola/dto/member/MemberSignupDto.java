package com.dreamteam.hola.dto.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
@Getter
public class MemberSignupDto {

    @Schema(hidden = true)
    @JsonIgnore
    private Long id;

    @Schema(description = "이메일 형식을 지킨 회원가입 할 email", example = "demomember@weco.com", required = true)
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Setter
    @Schema(description = "영문 대 소문자, 숫자, 특수문자를 사용를 사용한 8~16자의 회원가입 할 password", example = "asdfqwer1234~", required = true)
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @Schema(description = "특수문자를 제외한 2~10자리의 회원가입 할 nickname", example = "weco", required = true)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣A-Za-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    private String nickname;

    @Schema(description = "작성된 게시글 id",defaultValue = "1",example = "4",hidden = true)
    private String profileImage;

    @Schema(description = "작성된 게시글 id",defaultValue = "1",example = "4",hidden = true)
    private String socialType;

    /**
      *  후에 MemberSignupDto에 field로 생성자에 들어 갈 필요 없는 field들이 있을 수 있으므로
      *  @Builder 와 @AllArgsConstructor를 함께 쓰는 방식보다 필요한 생성자 요소를 넣어주는 @Builder가 더 유지보수 적으로 좋을 것이라 생각
      */
    @Builder
    public MemberSignupDto (String email, String password, String nickname, String profileImage, String socialType) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.socialType = socialType;
    }
}
