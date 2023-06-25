package sopt.org.FourthSeminar.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sopt.org.FourthSeminar.domain.SocialPlatform;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SocialLoginRequestDto {

    private SocialPlatform socialPlatform;
}
