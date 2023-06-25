package sopt.org.FourthSeminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sopt.org.FourthSeminar.domain.SocialPlatform;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/***
 * Controller 단에서 어떻게 소셜로그인의 종류를 구분하고 각각에 대한 로그인 처리를할 수 있을까?
 * API 하나로 만드는 것은 비효율적!
 * 클라이언트가 어떤 플랫폼인지를 요청에 함께 보내주면 이 정보를 이용해 구분하도록 Provider 클래스를 하나 더 생성
 *
 */
@Component
@RequiredArgsConstructor
public class SocialServiceProvider {

    // 구현한 소셜 서비스를 Map 에 모두 추가해준다. -> 클라이언트에서 받은 플랫폼 정보를 key 값으로 저장
    private static final Map<SocialPlatform, SocialService> socialServiceMap = new HashMap<>();

    private final KakaoSocialService kakaoSocialService;

    @PostConstruct
    void initializeSocialServiceMap() {
        socialServiceMap.put(SocialPlatform.KAKAO, kakaoSocialService);
    }

    public SocialService getSocialService(SocialPlatform socialPlatform) {
        return socialServiceMap.get(socialPlatform);
    }
}
