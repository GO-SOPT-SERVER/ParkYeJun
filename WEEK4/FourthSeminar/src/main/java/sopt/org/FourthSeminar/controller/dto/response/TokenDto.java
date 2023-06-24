package sopt.org.FourthSeminar.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class TokenDto {

    private String accessToken;
    private String refreshToken;
}
