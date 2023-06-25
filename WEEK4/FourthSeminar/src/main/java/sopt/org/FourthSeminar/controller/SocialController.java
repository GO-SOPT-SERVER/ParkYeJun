package sopt.org.FourthSeminar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sopt.org.FourthSeminar.common.dto.ApiResponse;
import sopt.org.FourthSeminar.controller.dto.request.SocialLoginRequestDto;
import sopt.org.FourthSeminar.exception.Success;
import sopt.org.FourthSeminar.service.SocialService;
import sopt.org.FourthSeminar.service.SocialServiceProvider;
import sopt.org.FourthSeminar.service.dto.request.SocialLoginRequest;

/**
 * Authorization의 기본은 Header에 정보를 담아서 요청을 보내는 것을 권장!
 * => code를 헤더에 실어서 보내도록 구현하자 (이 code는 단발성이기 떄문에 한 번 잘못되면 다시 발급받도록 해야 함)
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/social")
public class SocialController {

    private final SocialServiceProvider socialServiceProvider;

    @PostMapping("/login")
    public ApiResponse<Long> login(@RequestHeader("code") String code, @RequestBody SocialLoginRequestDto request) {

        /**
         * findByUserId와 같이 이미 등록된 유저인지에 대한 검사를 먼저 한 뒤에 소셜로그인 로직이 돌아가도록 하는 절차가 필요하다.
         */
        SocialService socialService = socialServiceProvider.getSocialService(request.getSocialPlatform());
        return ApiResponse.success(Success.SOCIAL_LOGIN_SUCCESS, socialService.login(SocialLoginRequest.of(code)));
    }
}
