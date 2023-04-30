package sort.org.ThirdSeminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sort.org.ThirdSeminar.controller.dto.request.UserRequestDto;
import sort.org.ThirdSeminar.controller.dto.response.UserResponseDto;
import sort.org.ThirdSeminar.domain.User;
import sort.org.ThirdSeminar.infrastructure.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원가입(User 생성)
     */
    @Transactional  // 트랜잭션 처리를 위한 어노테이션
    public UserResponseDto create(UserRequestDto request) {
        User user = User.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .password(request.getPassword())
                .build();

        userRepository.save(user);

        return UserResponseDto.of(user.getId(), user.getNickname());
    }

    /**
     * ID로 User 조회하기
     */
    public UserResponseDto getUser(Long userId) {
        return UserResponseDto.of(userRepository.findById(userId));
    }

}
