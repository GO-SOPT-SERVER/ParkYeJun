package sopt.org.FourthSeminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sopt.org.FourthSeminar.domain.SocialPlatform;
import sopt.org.FourthSeminar.domain.SocialUser;
import sopt.org.FourthSeminar.external.client.kakao.KakaoApiClient;
import sopt.org.FourthSeminar.external.client.kakao.KakaoAuthApiClient;
import sopt.org.FourthSeminar.external.client.kakao.dto.response.KakaoAccessTokenResponse;
import sopt.org.FourthSeminar.external.client.kakao.dto.response.KakaoUserResponse;
import sopt.org.FourthSeminar.infrastructure.SocialUserRepository;
import sopt.org.FourthSeminar.service.dto.request.SocialLoginRequest;

/**
 * Kakao Developer Docs에 명시된 필드 값을 가져와서 소셜 로그인을 하는 SocialUser에 대한 로직을 처리하는 클래스
 * 참고: https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#request-token
 */
@Service
@RequiredArgsConstructor
public class KakaoSocialService extends SocialService {

    @Value("${kakao.client-id}")
    private String clientId;

    private final SocialUserRepository socialUserRepository;

    private final KakaoAuthApiClient kakaoAuthApiClient;
    private final KakaoApiClient kakaoApiClient;

    // TODO Bearer를 붙여서 Access Token 값을 가져오기!
    // -> Access Token 자체로 클라한테 받아오는 것이 구현하기에 편리함

    @Override
    public Long login(SocialLoginRequest request) {

        System.out.println(clientId);

        // Authorization code로 Access Token 불러오기
        KakaoAccessTokenResponse tokenResponse = kakaoAuthApiClient.getOAuth2AccessToken(
                "authorization_code",
                clientId,
                "http://localhost:8080/kakao/callback",
                request.getCode()
        );

        // Access Token으로 유저 정보 불러오기
        KakaoUserResponse userResponse = kakaoApiClient.getUserInformation("Bearer " + tokenResponse.getAccessToken());

        SocialUser user = SocialUser.of(
                userResponse.getKakaoAccount().getProfile().getNickname(),
                userResponse.getKakaoAccount().getProfile().getProfileImageUrl(),
                SocialPlatform.KAKAO,
                tokenResponse.getAccessToken(),
                tokenResponse.getRefreshToken()
        );

        socialUserRepository.save(user);

        return user.getId();
    }
}
