package sopt.org.FourthSeminar.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "유저 생성 DTO")   // swagger
public class UserRequestDto {

    // [참고] @Email 만 붙이먄 null 값을 허용해준다.
    @Email(message = "이메일 형식에 맞지 않습니다.")
    @NotNull
    @Schema(description = "유저 이메일")
    private String email;

    @NotBlank
    @Pattern(regexp = "^[가-힣a-zA-Z]{2,10}$", message = "닉네임 형식에 맞지 않습니다.")
    @Schema(description = "유저 닉네임")
    private String nickname;

    @NotBlank
    @Pattern(
            regexp = "(?=.*[0-9])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자~20자의 비밀번호여야 합니다."
    )
    @Schema(description = "유저 비밀번호")
    private String password;
}
