package sopt.org.FourthSeminar.external.client.kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import sopt.org.FourthSeminar.external.client.kakao.dto.response.KakaoUserResponse;


/**
 * 카카오 사용자의 정보를 불러오기 위한 클래스
 * - 받아온 Authorization Token 정보만 보내주면 된다!
 */
@FeignClient(name = "kakaoApiClient", url = "https://kapi.kakao.com")
public interface KakaoApiClient {

    @GetMapping(value = "/v2/user/me")
    KakaoUserResponse getUserInformation(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken);


}