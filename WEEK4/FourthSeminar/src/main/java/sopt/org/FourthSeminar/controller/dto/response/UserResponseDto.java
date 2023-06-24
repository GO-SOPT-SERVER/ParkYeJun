package sopt.org.FourthSeminar.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import sopt.org.FourthSeminar.domain.User;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponseDto {

    private Long userId;
    private String nickname;

    // 받아온 User Entity 객체를 ResponseDto에 담아주는 과정
    public static UserResponseDto of(Long userId, String nickname) {
        return new UserResponseDto(userId, nickname);
    }

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getId(), user.getNickname());
    }
}
