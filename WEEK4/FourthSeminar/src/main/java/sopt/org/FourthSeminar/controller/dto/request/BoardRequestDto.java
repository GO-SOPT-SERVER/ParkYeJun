package sopt.org.FourthSeminar.controller.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardRequestDto {

    // 유저 조회를 이메일이 아닌 JWT 토큰 내의 유저 아이디로 하기 위해 이메일 주석처리!
//    @Email
//    private String email;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Boolean isPublic;
}
